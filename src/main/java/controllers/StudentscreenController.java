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
    
    private Boolean upwardAnimationNeeded = true;
    private Boolean closeButtonAnimationNeeded = false;
    
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
                student = new Student(
                        nameCleanup(surnameField.getText()), 
                        nameCleanup(nameField.getText()), 
                        emailField.getText(), "");
                ic.addStudent(student);
                }else{
                    student.setVoornaam(nameCleanup(surnameField.getText()));
                    student.setAchternaam(nameCleanup(nameField.getText()));
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
                animateChoice(newValue, 0);
            }
        });
        
        surnameField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                animateChoice(newValue, 57);
            }
        });
        
        emailField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                animateChoice(newValue, 114);
            }
        });
        
        closeTextArea.setOnMouseClicked((event)->
        {
            animateMove(-171, 0, stage.getScene().getRoot());
            upwardAnimationNeeded = true;
            closeButtonAnimationNeeded = false;
            stage.getScene().getRoot().requestFocus();
            closeTextArea.setVisible(false);
        });
    }
    
    public String errormessage(){
        StringBuilder sb = new StringBuilder();
        if(nameField.getText().replaceAll("\\s+","").isEmpty()){
            sb.append("Gelieve een achternaam in te vullen.\n");
        }
        
        if(surnameField.getText().replaceAll("\\s+","").isEmpty()){
            sb.append("Gelieve een voornaam in te vullen.\n");
        }
        
        if(emailField.getText().replaceAll("\\s+","").isEmpty()){
            sb.append("Gelieve een e-mailadres in te vullen.");
        } 
        else if (!emailField.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"))
        {
            sb.append("Gelieve een correct e-mailadres in te vullen.");
        }
        
        return sb.toString();
    }
    
    public void animateChoice(boolean newValue, int moveToValue)
    {
        if(newValue)
        {
            if (closeButtonAnimationNeeded)
            {
                animateMove(position, moveToValue, closeTextArea);
                position = moveToValue;
            }
            else
            {
                animateMove(moveToValue, moveToValue, closeTextArea);
                position = moveToValue;
            }   
            if (upwardAnimationNeeded)
            {
                animateMove(0, -171, stage.getScene().getRoot());
                upwardAnimationNeeded = false;
                closeButtonAnimationNeeded = true;
                closeTextArea.setVisible(true);
            }
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
    }
    
    public String nameCleanup (String name)
    {
        name = name.trim().replaceAll("\\s{2,}", " ");
        char[] chars = name.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
          if (!found && Character.isLetter(chars[i])) {
            chars[i] = Character.toUpperCase(chars[i]);
            found = true;
          } else if (Character.isWhitespace(chars[i]) || chars[i]=='\'' || chars[i]=='-') { // You can add other chars here
            found = false;
          }
        }
        
        return String.valueOf(chars);
    }
}
