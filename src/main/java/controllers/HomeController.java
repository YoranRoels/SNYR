/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.SwitchPanelCommand;
import domein.Student;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.DriveModel;
import models.SkillsModel;
import panels.AnchorSide;
import panels.AnchorWheel;



/**
 *
 * @author sande
 */
public class HomeController {
    
    @FXML
    private ImageView profielFoto;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Button terugknop;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Button minusButton;
    @FXML
    private Button plusButton;
    
    private AnchorWheel wheelpane;
    
    private AnchorSide sidepane;
    
    private final Stage stage;
    
    private final SkillsModel skillModel;
    
    private final DriveModel rijModel;
    
    private final Student student;
    
    private final InlogController ic;
    

    public HomeController(Stage stage,Student student,InlogController ic) {
        this.skillModel = new SkillsModel(student);
        this.rijModel=new DriveModel(student);
        this.stage = stage;
        this.student=student;
        this.ic=ic;
    }
    
    
    
    public void initialize(){
    //main setup initialize van de gui
        System.out.println("Start initliaze");
        wheelpane=new AnchorWheel(borderpane,rijModel);
        sidepane=new AnchorSide(borderpane,skillModel);

        wheelpane.setAnchorSide(sidepane);
        sidepane.setAnchorWheel(wheelpane);
        
        sidepane.create();
        wheelpane.create();
        
        borderpane.setCenter(wheelpane);
  
        //set profil pic van de user
        //profielFoto.setImage(null);
        
        /*klikken op de profiel foto brengt je naar de home/inlog page*/
        profielFoto.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));
                
                ic.updateStudent(student);
                loader.setController(ic);
                Parent root = (Parent) loader.load();
                
                
               // Scene scene = new Scene(root);
               // stage.setScene(scene);
               stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    });
        /*terugknop brengt je naar de student homepage met sturen */
        terugknop.setOnMouseClicked(new SwitchPanelCommand(borderpane, wheelpane));
        
        
        //set on action   ... 
        checkBox1.setOnAction((ActionEvent event) -> {
            System.out.println("register evalutation 1");
        });
        
        minusButton.setOnAction((ActionEvent event) -> {
            progressBar.progressProperty().setValue(progressBar.progressProperty().doubleValue()-0.05);
        });
        
        plusButton.setOnAction((ActionEvent event) -> {
            progressBar.progressProperty().setValue(progressBar.progressProperty().doubleValue()+0.05);
        });
    }
    
    @FXML
    protected void openProfiel(){
        System.out.println("Open profiel");
    }
    @FXML
    protected void swipeCenterPanel(){
        System.out.println("test");
        
    }
    
}
