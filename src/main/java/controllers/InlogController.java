/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import async.AddStudentTask;
import async.GetStudentListTask;
import domein.Student;
import domein.StudentenComparator;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 *
 * @author sande
 */
public class InlogController {
    
    @FXML
    private Button loginButton;
    @FXML
    private Button nieuwButton;
    @FXML
    private Button aanpasButton;
    
    @FXML
    private ListView<Student> studentenListView;
    
    /*dummy students*/
    private final ObservableList<Student> studenten = FXCollections.observableArrayList();
    
    private final ObservableList selectie;
    
    private final Stage stage;
    
    // Threading
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public InlogController(Stage stage,ObservableList<Student> studenten,ObservableList<String> selectie) {
        this.stage = stage;
//        this.studenten=studenten;
        this.selectie=selectie;
        GetStudentListTask task = new GetStudentListTask();
        task.setOnSucceeded(event -> {
            studenten.addAll(task.getValue());
        });
        task.setOnFailed(event -> {
            System.out.println("FAILED TO LOAD STUDENTS");
            task.getException().printStackTrace();
        });
        service.submit(task);
    }
    /*constructor, met als extra waarde de geupdate student*/
     
    public void initialize(){
        System.out.println("Inlog controller");

        //studenten.sort(comparator);
        Collections.sort(studenten, comparator);
        studentenListView.setItems(studenten);
        loginButton.setOnAction((ActionEvent event) -> {
            if(studentenListView.getSelectionModel().getSelectedItem()!=null){
            try {
                System.out.println("open studenten fiche");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/HomeScreen.fxml"));
                
                /*stage en de gekozen student doorgeven*/
                loader.setController(new HomeController(stage,studentenListView.getSelectionModel().getSelectedItem(),this,selectie));
                Parent root = (Parent) loader.load();
                
                //Scene scene = new Scene(root);
                //stage.setScene(scene);
                stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else{
                /*foutmelding geen gesecteerde student*/
                System.out.println("FOUT GEEN STUDENT");
            }
        });
        nieuwButton.setOnAction((ActionEvent event) ->{
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/StudentScreen.fxml"));
            loader.setController(new StudentscreenController(stage,this));
            Parent root = (Parent) loader.load();
            stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        aanpasButton.setOnAction((ActionEvent event) ->{
             if(studentenListView.getSelectionModel().getSelectedItem()!=null){
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/StudentScreen.fxml"));
            loader.setController(new StudentscreenController(stage,studentenListView.getSelectionModel().getSelectedItem(),this));
            Parent root = (Parent) loader.load();
            stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
             }
            });
        
          
        
    }
    /*comp om op achternaam te sorteren*/
    private final StudentenComparator comparator=new StudentenComparator();
    public void updateStudent(Student student){
        /*de oude file weg en vervangen door de nieuwe*/
        System.out.println(studenten.indexOf(student));
        studenten.remove(studenten.indexOf(student));
        studenten.add(student);
       // studenten.sort(comparator);
       Collections.sort(studenten, comparator);
        studentenListView.setItems(studenten);
        
    }
    
    public void addStudent(Student student){
        AddStudentTask task = new AddStudentTask(student);
        task.setOnSucceeded(event -> {
            studenten.add(student);
        });
        task.setOnFailed(event -> {
            System.out.println("STUDENT CREATION FAILED");
            task.getException().printStackTrace();
        });
        service.submit(task);
//        studenten.add(student);
        Collections.sort(studenten, comparator);
//        studentenListView.setItems(studenten);
    }
}
