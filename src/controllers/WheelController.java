/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.SwitchPanelCommand;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import panels.AnchorAttitude;
import panels.AnchorDrive;

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
    
    public void initialize(){
        System.out.println("Wheelcontroller");
        drivePane = new AnchorDrive(root);
        drivePane.create();
        driveButton.setOnMouseClicked(new SwitchPanelCommand(root, drivePane));
        
        attitudePane= new AnchorAttitude(root);
        attitudePane.create();
        attidudeButton.setOnMouseClicked(new SwitchPanelCommand(root, attitudePane));

    }
    
    public WheelController(BorderPane root)
    {
        this.root=root;
    }
}
