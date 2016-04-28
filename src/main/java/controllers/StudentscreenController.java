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
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sande
 */
public class StudentscreenController {
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField studentNrField;
    @FXML
    private TextField emailField;
    @FXML
    private Button opslaan;
    @FXML
    private Label errorMessageLabel;


    private Student student;
    private Stage stage;
    private InlogController ic;
    
    public StudentscreenController(Stage stage,InlogController ic) {

        this.ic=ic;
        this.stage=stage;
    }
    
    public StudentscreenController(Stage stage,Student student, InlogController ic) {
        this.stage=stage;
        this.ic=ic;
    }
    
    
    public void initialize(){
        opslaan.setOnAction((ActionEvent event)->{
            String errormessage=errormessage();
            if(errormessage.isEmpty()){
                /*ok*/
                student = new Student(surnameField.getText(), nameField.getText(), emailField.getText(), "");
                ic.addStudent(student);
                        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));
                
                ic.updateStudent(student);
                loader.setController(ic);
                Parent root = (Parent) loader.load();

               stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            }
            else{
                errorMessageLabel.setText(errormessage);
            }
        });
    }
    
    public String errormessage(){
        StringBuilder sb = new StringBuilder();
        if(nameField.getText().isEmpty()){
            sb.append("Er werd geen naam opgegeven \n");
        }
        if(surnameField.getText().isEmpty()){
            sb.append("Er is geen voornaam opgegeven \n");
        }
        if(studentNrField.getText().isEmpty()){
            sb.append("Er is geen studenten Nummer");
        }
        if(!studentNrField.getText().matches("[0-9][0-9]*")){
            sb.append("Geen geldig studenten nr");
        }
        
        return sb.toString();
    }
    
}
