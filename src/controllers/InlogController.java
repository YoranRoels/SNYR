/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.HomeController;
import domein.Student;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import main.RijschoolEva;
import panels.AnchorWheel;

/**
 *
 * @author sande
 */
public class InlogController {
    
    @FXML
    private Button loginButton;
    
    @FXML
    private ListView<Student> studentenListView;
    
    /*dummy students*/
    private ObservableList<Student> studenten;
    
    private final Stage stage;

    public InlogController(Stage stage,ObservableList<Student> studenten) {
        this.stage = stage;
        this.studenten=studenten;
    }
    /*constructor, met als extra waarde de geupdate student*/

    
    
    public void initialize(){
        System.out.println("Inlog controller");

        
        
        studentenListView.setItems(studenten);
        loginButton.setOnAction((ActionEvent event) -> {
            if(studentenListView.getSelectionModel().getSelectedItem()!=null){
            try {
                System.out.println("open studenten fiche");
                FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("HomeScreen.fxml"));
                
                /*stage en de gekozen student doorgeven*/
                loader.setController(new HomeController(stage,studentenListView.getSelectionModel().getSelectedItem(),this));
                Parent root = (Parent) loader.load();
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else{
                /*foutmelding geen gesecteerde student*/
                System.out.println("FOUT GEEN STUDENT");
            }
        });
        
        
        
    }
    
    public void updateStudent(Student student){
        /*de oude file weg en vervangen door de nieuwe*/
        studenten.remove(studenten.indexOf(student));
        studenten.add(student);
        
        studentenListView.setItems(studenten);
        
    }
    
}
