/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Student;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    
    private final ObservableList<Student> studenten;

    private Student student;
    
    public StudentscreenController(ObservableList<Student> studenten) {
        this.studenten = studenten;
    }
    
    public StudentscreenController(ObservableList<Student> studenten,Student student) {
        this.studenten = studenten;
    }
    
    
    public void initialize(){
        opslaan.setOnAction((ActionEvent event)->{
            String errormessage=errormessage();
            if(errormessage.isEmpty()){
                /*ok*/
                student = new Student(surnameField.getText(), nameField.getText(), emailField.getText(), "");
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
