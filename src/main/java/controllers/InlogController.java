/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import async.AddStudentTask;
import async.DeleteStudentTask;
import async.GetStudentListTask;
import async.UpdateStudentsTask;
import domein.Student;
import domein.StudentenComparator;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.ConfirmationDialogModel;

/**
 *
 * @author sande
 */
public class InlogController {
    
    @FXML
    private Label studentLabel;
    @FXML
    private ImageView profilePic;
    @FXML
    private Button loginButton;
    @FXML
    private Button nieuwButton;
    @FXML
    private Button aanpasButton;
    @FXML
    private Button verwijderButton;
    @FXML
    private Button syncButton;
    
    @FXML
    private ListView<Student> studentenListView; 
    
    // Students
    private final ObservableList<Student> studenten = FXCollections.observableArrayList();
    
    private final ObservableList selectie;
    
    private final Stage stage;
    
    private ConfirmationDialogModel confirmationDialogModel = new ConfirmationDialogModel();
    
    // Threading
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public InlogController(Stage stage,ObservableList<String> selectie) {
        this.stage = stage;
        this.selectie=selectie;
    
        updateStudentenLijstFromBackend();
    }
    /*constructor, met als extra waarde de geupdate student*/
     
    public void initialize(){
        System.out.println("Inlog controller");
        
        Collections.sort(studenten, comparator);
        studentenListView.setItems(studenten);
        
        loginButton.setOnAction((ActionEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/HomeScreen.fxml"));

                /*stage en de gekozen student doorgeven*/
                loader.setController(new HomeController(stage,studentenListView.getSelectionModel().getSelectedItem().openStudent(),this,selectie));
                Parent root = (Parent) loader.load();

                //Scene scene = new Scene(root);
                //stage.setScene(scene);
                stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /*open scherm voor een nieuwe student toetevoegen*/
        nieuwButton.setOnAction((ActionEvent event) ->
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/StudentScreen.fxml"));
                loader.setController(new StudentscreenController(stage,this));
                Parent root = (Parent) loader.load();
                stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        /*open het aanpas scherm van de student(algemene gegevens,name)*/
        aanpasButton.setOnAction((ActionEvent event) ->{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/StudentScreen.fxml"));
                loader.setController(new StudentscreenController(stage,studentenListView.getSelectionModel().getSelectedItem(),this));
                Parent root = (Parent) loader.load();
                stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        verwijderButton.setOnAction((ActionEvent event) -> {
//            studentenListView.getItems().remove(studentenListView.getSelectionModel().getSelectedIndex());
//            studentenListView.getSelectionModel().select(-1); //Unselect listview
            /*gelesteerde studenten aan de del task geven*/
            DeleteStudentTask deltask = new DeleteStudentTask(studentenListView.getItems().get(studentenListView.getSelectionModel().getSelectedIndex()));
            deltask.setOnSucceeded(delvent -> {
                /*boodschap eventueel?*/
            });
            deltask.setOnFailed(delvent -> {
                System.out.println("ERROR DELETE STUDENT");
                deltask.getException().printStackTrace();
            });
            service.submit(deltask);
            /*lijst terug updaten*/
            updateStudentenLijstFromBackend();
            
            disableChoiceAndResetLabel();
        });
        
        syncButton.setOnAction((ActionEvent event) -> {
            Parent root;
            try 
            {
                // Load the fxml with the fitting dialog
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/ConfirmationDialog.fxml"));
                // Pass on the message that is needed as the top label
                //         the model to check if the user accepted
                loader.setController(new ConfirmationDialogController(
                        "Wilt u de studenten opslaan?", 
                        confirmationDialogModel));
                Parent content = (Parent) loader.load();
                // Create the stage
                Stage dialogStage = new Stage();
                // The title of the window (not visible on android)
                dialogStage.setTitle("Opslaan");
                // Disables the main window when the dialog is open.
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(stage.getScene().getWindow());
                // Make the fxml the scene
                dialogStage.setScene(new Scene(content));
                // show the dialog and wait till it closes to continue the code
                stage.show();
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if(confirmationDialogModel.isAccepted())
            {
                System.out.println("Syncing");

                /*alleen de studenten doorgeven waar changes aan gebeurde*/
                ObservableList<Student> studentenFiltered = FXCollections.observableArrayList();

                for(Student student : studenten)
                {
                    if (student.getStudentOpened())
                    {
                        studentenFiltered.add(student);
                    }
                }
                
                UpdateStudentsTask updatetask = new UpdateStudentsTask(new ArrayList<>(studentenFiltered));

                updatetask.setOnSucceeded(upevent ->{
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    // get current date time with Date()
                    System.out.println("Sync on "+dateFormat.format(new Date()));
                    updateStudentenLijstFromBackend();
                    try {
                        // Load the fxml with the fitting dialog
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InfoDialog.fxml"));
                        // Pass on the message that is needed as the top label
                        //         the model to check if the user accepted
                        loader.setController(new InfoDialogController(
                                "De studenten zijn opgeslaan."));
                        Parent content = (Parent) loader.load();
                        // Create the stage
                        Stage dialogStage = new Stage();
                        // The title of the window (not visible on android)
                        dialogStage.setTitle("Opgeslaan");
                        // Disables the main window when the dialog is open.
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        dialogStage.initOwner(stage.getScene().getWindow());
                        // Make the fxml the scene
                        dialogStage.setScene(new Scene(content));
                        // show the dialog and wait till it closes to continue the code
                        dialogStage.showAndWait();
                        // Set stage opacity back to normal
                        stage.setOpacity(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                updatetask.setOnFailed(upevent -> {
                    System.out.println("Sync failed");
                    updatetask.getException().printStackTrace();
                    try {
                        // Load the fxml with the fitting dialog
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/ErrorDialog.fxml"));
                        // Pass on the message that is needed as the top label
                        //         the model to check if the user accepted
                        loader.setController(new ErrorDialogController(
                                "De studenten zijn NIET opgeslaan!"));
                        Parent content = (Parent) loader.load();
                        // Create the stage
                        Stage dialogStage = new Stage();
                        // The title of the window (not visible on android)
                        dialogStage.setTitle("ERROR");
                        // Disables the main window when the dialog is open.
                        dialogStage.initModality(Modality.WINDOW_MODAL);
                        dialogStage.initOwner(stage.getScene().getWindow());
                        // Make the fxml the scene
                        dialogStage.setScene(new Scene(content));
                        // show the dialog and wait till it closes to continue the code
                        dialogStage.showAndWait();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                service.submit(updatetask);
            }
        });
        
        // Block labels & options.
        disableChoiceAndResetLabel();
        
        studentenListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() 
        {
            @Override
            public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) 
            {   
                if (newValue != null)
                {
                    studentLabel.setText(newValue.getAchternaam() + " " + newValue.getVoornaam());
                    loginButton.setDisable(false);
                    aanpasButton.setDisable(false);
                    verwijderButton.setDisable(false);
                    profilePic.setOpacity(1);
                    studentLabel.setOpacity(1);
                }
            }
        });
        // End code for blocking labels & options.
    }
    
    public void disableChoiceAndResetLabel()
    {
        studentLabel.setText("Student"); // Zodat bij het inkomen van de app of aanpassing/nieuwe student er altijd "Student" staat bij het terugkeren naar de inlogpane.
        loginButton.setDisable(true);
        aanpasButton.setDisable(true);
        verwijderButton.setDisable(true);
        profilePic.setOpacity(0.25);
        studentLabel.setOpacity(0.25);
    }
    
    
    // Comparator to sort last & first name.
    private final StudentenComparator comparator=new StudentenComparator();
    
    public void updateStudent(Student student)
    {
        // Delete the old file and replace it by the new one.
        studenten.remove(studenten.indexOf(student));
        studenten.add(student);
        // Sort the students by last & firstname.
        Collections.sort(studenten, comparator);
        // Set the listview.
        studentenListView.setItems(studenten);
        // Make the updatetask.
        UpdateStudentsTask updatetask = new UpdateStudentsTask(studenten);
        updatetask.setOnFailed(event -> {
            System.out.println("Updating students FAILED.");
            updatetask.getException().printStackTrace();
        });
        // Start the updatetask.
        service.submit(updatetask);
    }
    
    public void addStudent(Student student){
        AddStudentTask addtask = new AddStudentTask(student);
        addtask.setOnSucceeded(event -> {
            studenten.add(student);
        });
        addtask.setOnFailed(event -> {
            System.out.println("STUDENT CREATION FAILED");
            addtask.getException().printStackTrace();
        });
        service.submit(addtask);
        
        updateStudentenLijstFromBackend();
    }
    
    
    private void updateStudentenLijstFromBackend(){
        
        //studenten lijst updaten
        GetStudentListTask gettask = new GetStudentListTask();
        gettask.setOnSucceeded(event -> {
            studenten.clear();
            //List<Student> value = gettask.getValue();
            studenten.setAll(gettask.getValue());
            Collections.sort(studenten, comparator);
            studentenListView.setItems(studenten);
    
        });
        gettask.setOnFailed(event -> {
            System.out.println("FAILED TO LOAD STUDENTS");
            gettask.getException().printStackTrace();
//            
            System.out.println("Sync failed");
        });
        service.submit(gettask);
    }
}
