/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.SwitchPanelCommand;
import domein.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import models.RijModel;
import panels.*;

/**
 *
 * @author sande
 */
public class WheelController {
    
    @FXML
    private Button driveButton;
    @FXML
    private Button trafficButton;
    @FXML 
    private Button attidudeButton;
    
    private final BorderPane root;
    
    private AnchorDrive drivePane;
    private AnchorAttitude attitudePane;
    private AnchorTraffic trafficPane;
    
    private final RijModel rijModel;
    
    public void initialize(){
        System.out.println("Wheelcontroller");
        drivePane = new AnchorDrive(root,rijModel);
        drivePane.create();
        driveButton.setOnMouseClicked(new SwitchPanelCommand(root, drivePane));
        
        attitudePane= new AnchorAttitude(root);
        attitudePane.create();
        attidudeButton.setOnMouseClicked(new SwitchPanelCommand(root, attitudePane));
        
        trafficPane = new AnchorTraffic(root);
        trafficPane.create();
        trafficButton.setOnMouseClicked(new SwitchPanelCommand(root, trafficPane));
    }
    
    public WheelController(BorderPane root,RijModel rijModel)
    {
        this.root=root;
       this.rijModel=rijModel;
    }
}
