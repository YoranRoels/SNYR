/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.SwitchPanelCommand;
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
import models.SwitchModel;
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
    
    private final SwitchModel switchModel = new SwitchModel();
    
    

    public HomeController(Stage stage) {
        this.stage = stage;
    }
    
    
    
    public void initialize(){
    //main setup initialize van de gui
        System.out.println("Start initliaze");
        wheelpane=new AnchorWheel(borderpane);
        sidepane=new AnchorSide(borderpane);

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
                FXMLLoader loader = new FXMLLoader(InlogController.class.getResource("InlogScreen.fxml"));
                
                
                loader.setController(new InlogController(stage));
                Parent root = (Parent) loader.load();
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
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
