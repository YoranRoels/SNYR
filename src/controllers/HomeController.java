/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.SwitchModel;
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
    
    private AnchorWheel wheelpane;
    
    
    private final SwitchModel switchModel = new SwitchModel();
    
    
    public void initialize(){
    //main setup initialize van de gui
        System.out.println("Start initliaze");
        wheelpane=new AnchorWheel();
        wheelpane.create();
        
        borderpane.setCenter(wheelpane);
        //wheelpane.create();
        //set profil pic van de user
        //profielFoto.setImage(null);
        
        //set on action   ... 
        checkBox1.setOnAction((ActionEvent event) -> {
            System.out.println("register evalutation 1");
                    });
    }
    
    
    public void openProfiel(){
        System.out.println("Open profiel");
    }
    
    public void swipeCenterPanel(){
        System.out.println("test");
        
    }
    
}
