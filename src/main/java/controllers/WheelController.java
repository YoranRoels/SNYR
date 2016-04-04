/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import panels.AnchorTraffic;
import panels.AnchorAttitude;
import panels.AnchorDrive;
import commands.SwitchPanelCommand;
import domein.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import models.AttitudeModel;
import models.DriveModel;
import models.TrafficModel;

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
    
    private final DriveModel driveModel;
    private final TrafficModel trafficModel;
    private final AttitudeModel attitudeModel;
    
    public void initialize(){
        System.out.println("Wheelcontroller");
        drivePane = new AnchorDrive(root,driveModel);
        drivePane.create();
        driveButton.setOnMouseClicked(new SwitchPanelCommand(root, drivePane));
        
        attitudePane= new AnchorAttitude(root,attitudeModel);
        attitudePane.create();
        attidudeButton.setOnMouseClicked(new SwitchPanelCommand(root, attitudePane));
        
        trafficPane = new AnchorTraffic(root,trafficModel);
        trafficPane.create();
        trafficButton.setOnMouseClicked(new SwitchPanelCommand(root, trafficPane));
    }
    
    public WheelController(BorderPane root,DriveModel driveModel,TrafficModel trafficModel,AttitudeModel attitudeModel)
    {
        this.root=root;
       this.driveModel=driveModel;
       this.trafficModel=trafficModel;
       this.attitudeModel=attitudeModel;
    }
}
