/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.HomeController;
import domein.Student;
import domein.StudentenComparator;
import java.io.IOException;
import java.util.Collections;
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
import start.RijschoolEva;
import panels.AnchorWheel;

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
    private ListView<Student> studentenListView;
    
    /*dummy students*/
    private final ObservableList<Student> studenten;
    
    private final ObservableList selectie;
    
    private final Stage stage;

    public InlogController(Stage stage,ObservableList<Student> studenten,ObservableList<String> selectie) {
        this.stage = stage;
        this.studenten=studenten;
        this.selectie=selectie;
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
            loader.setController(new StudentscreenController(studenten));
            Parent root = (Parent) loader.load();
            stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
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
    
}
