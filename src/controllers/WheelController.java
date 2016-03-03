/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.SwitchPanelCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
    
    private BorderPane root;
    
    private AnchorDrive drivePane;
    
    public void initialize(){
        System.out.println("Wheelcontroller");
        drivePane = new AnchorDrive(root);
        drivePane.create();
        driveButton.setOnMouseClicked(new SwitchPanelCommand(root, drivePane));
    }
    
    public WheelController(BorderPane root)
    {
        this.root=root;
    }
}
