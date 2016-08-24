/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Student;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author sande
 */
public class StudentscreenController {
    
    @FXML
    private Button terugknopNewStudent;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private Button opslaan;
    @FXML
    private Label errorMessageLabel;
    @FXML
    private StackPane closeTextArea;
    
    private Boolean animationNeeded = true;
    
    private int position = 0;


    private Student student;
    private Stage stage;
    private InlogController ic;
    
    public StudentscreenController(Stage stage,InlogController ic) {

        this.ic=ic;
        this.stage=stage;
    }
    
    /*aanpas constructor*/
    public StudentscreenController(Stage stage,Student student, InlogController ic) {
        this.stage=stage;
        this.ic=ic;
        this.student=student;
    }
    
    public void initialize(){
        System.out.println("StudentscreenController");
        
        /*als het aanpassen is al invullen*/
        if(student!=null){
            nameField.setText(student.getAchternaam());
            surnameField.setText(student.getVoornaam());
            emailField.setText(student.getEmail());
        }
        
        /*opslaan actie*/
        opslaan.setOnAction((ActionEvent event)->{
            String errormessage=errormessage();
            if(errormessage.isEmpty()){
                /*ok, opslaan of aanpassen*/
                if(student==null){
                student = new Student(surnameField.getText(), nameField.getText(), emailField.getText(), "");
                ic.addStudent(student);
                }else{
                    student.setVoornaam(surnameField.getText());
                    student.setAchternaam(nameField.getText());
                    student.setEmail(emailField.getText());
                    ic.updateStudent(student);
                }
                        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));
                
                loader.setController(ic);
                Parent root = (Parent) loader.load();

               stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            else{
                /*foutmelding*/
                errorMessageLabel.setText(errormessage);
            }
        });
        
        /*knop om gewoon terug te keren*/
        terugknopNewStudent.setOnAction((ActionEvent event) ->{
            try 
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));
                
                loader.setController(ic);
                Parent root = (Parent) loader.load();

                stage.getScene().setRoot(root);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        closeTextArea.setVisible(false);
        
        nameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                animateChoice(newValue);
                if (newValue)
                {
                    if (closeTextArea.isVisible())
                    {
                        animateMove(position, 0, closeTextArea);
                        position = 0;
                    }
                    else
                    {
                        animateMove(0, 0, closeTextArea);
                    }
                }
            }
        });
        
        surnameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                animateChoice(newValue);
                if (newValue)
                {
                    if (closeTextArea.isVisible())
                    {
                        animateMove(position, 57, closeTextArea);
                        position = 57;
                    }
                    else
                    {
                        animateMove(0, 0, closeTextArea);
                    }
                }
            }
        });
        
        emailField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                animateChoice(newValue);
                if (newValue)
                {
                    if (closeTextArea.isVisible())
                    {
                        animateMove(position, 114, closeTextArea);
                        position = 114;
                    }
                    else
                    {
                        animateMove(0, 0, closeTextArea);
                    }
                }
            }
        });
        
        closeTextArea.setOnMouseClicked((event)->{
            if (animationNeeded == false)
            {
                animateMove(-171, 0, stage.getScene().getRoot());
                animationNeeded = true;
                stage.getScene().getRoot().requestFocus();
                closeTextArea.setVisible(false);
            }
        });
    }
    
    public String errormessage(){
        StringBuilder sb = new StringBuilder();
        if(nameField.getText().isEmpty()){
            sb.append("Gelieve een achternaam in te vullen.\n");
        }
        if(surnameField.getText().isEmpty()){
            sb.append("Gelieve een voornaam in te vullen.\n");
        }
        
        return sb.toString();
    }
    
    public void animateChoice(boolean newValue)
    {
        if(animationNeeded && newValue)
        {
            animateMove(0, -171, stage.getScene().getRoot());
            animationNeeded = false;
        }
    }
    
    public void animateMove(int from, int to, Node node)
    {
//        if(to == 0)
//        {
//            closeTextArea.setVisible(false);
//        }
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), node);
        translateTransition.setFromY(from);
        translateTransition.setToY(to);
        translateTransition.play();
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                if(!animationNeeded)
                {
                    closeTextArea.setVisible(true);
                }
            }
        });
    }
}
