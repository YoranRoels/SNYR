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
import domein.Color;
import java.util.ArrayList;
import java.util.Collections;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import models.AttitudeModel;
import models.DriveModel;
import models.TrafficModel;

/**
 *
 * @author sande
 */
public class WheelController implements InvalidationListener {
    
    @FXML
    private Button driveButton;
    @FXML
    private Button trafficButton;
    @FXML 
    private Button attidudeButton;
    @FXML
    private Rectangle driveTopIndicator;
    @FXML
    private Rectangle driveLeftIndicator;
    @FXML
    private Rectangle driveRightIndicator;
    @FXML
    private Rectangle trafficLeftIndicator;
    @FXML
    private Rectangle trafficRightIndicator;
    @FXML
    private Rectangle trafficBottomIndicator;
    @FXML
    
    private final BorderPane root;
    
    private AnchorDrive drivePane;
    private AnchorAttitude attitudePane;
    private AnchorTraffic trafficPane;
    
    private final DriveModel driveModel;
    private final TrafficModel trafficModel;
    private final AttitudeModel attitudeModel;
    
    public void initialize(){
        System.out.println("Wheelcontroller");
        update();
        drivePane = new AnchorDrive(root,driveModel);
        drivePane.create();
        driveButton.setOnMouseClicked(new SwitchPanelCommand(root, drivePane));
        
        attitudePane= new AnchorAttitude(root,attitudeModel);
        attitudePane.create();
        attidudeButton.setOnMouseClicked(new SwitchPanelCommand(root, attitudePane));
        
        trafficPane = new AnchorTraffic(root,trafficModel);
        trafficPane.create();
        trafficButton.setOnMouseClicked(new SwitchPanelCommand(root, trafficPane));
        
        driveModel.addListener(this); // kleuren in stuur aanpassen
        trafficModel.addListener(this); // kleuren in bord aanpassen
    }
    
    public WheelController(BorderPane root,DriveModel driveModel,TrafficModel trafficModel,AttitudeModel attitudeModel)
    {
        this.root=root;
       this.driveModel=driveModel;
       this.trafficModel=trafficModel;
       this.attitudeModel=attitudeModel;
    }

    @Override
    public void invalidated(Observable observable) {
        System.out.println("Invalidated WheelController");
        update();
    }
    
    public void update()
    {
        ArrayList<Color> driveTopColors = new ArrayList<>();
        driveTopColors.add(driveModel.getPostureColor());
        driveTopColors.add(driveModel.getClutchColor());
        driveTopColors.add(driveModel.getBrakingColor());
        driveTopColors.add(driveModel.getSteeringColor());
        driveTopColors.add(driveModel.getShiftingColor());
        driveTopColors.add(driveModel.getLookingColor());
        
        changeRectangleColor(driveTopColors, driveTopIndicator);
        
        ArrayList<Color> driveLeftColors = new ArrayList<>();
        driveLeftColors.add(driveModel.getHillColor());
        driveLeftColors.add(driveModel.getSteeringPracticeColor());
        driveLeftColors.add(driveModel.getReverseColor());
        driveLeftColors.add(driveModel.getGarageColor());
        
        changeRectangleColor(driveLeftColors, driveLeftIndicator);
        
        ArrayList<Color> driveRightColors = new ArrayList<>();
        driveRightColors.add(driveModel.getTurningColor());
        driveRightColors.add(driveModel.getParkingColor());
        
        changeRectangleColor(driveRightColors, driveRightIndicator);
        
        ArrayList<Color> trafficLeftColors = new ArrayList<>();
        trafficLeftColors.add(trafficModel.getIndicatorsColor());
        trafficLeftColors.add(trafficModel.getCrossingColor());
        trafficLeftColors.add(trafficModel.getOvertakingColor());
        
        changeRectangleColor(trafficLeftColors, trafficLeftIndicator);
        
        ArrayList<Color> trafficRightColors = new ArrayList<>();
        trafficRightColors.add(trafficModel.getPublicroadColor());
        trafficRightColors.add(trafficModel.getTurningleftColor());
        trafficRightColors.add(trafficModel.getTurningrightColor());
        trafficRightColors.add(trafficModel.getDistanceColor());
        
        changeRectangleColor(trafficRightColors, trafficRightIndicator);
        
        ArrayList<Color> trafficBottomColors = new ArrayList<>();
        trafficBottomColors.add(trafficModel.getPriorityColor());
        trafficBottomColors.add(trafficModel.getSignColor());
        trafficBottomColors.add(trafficModel.getSpeedColor());
        
        changeRectangleColor(trafficBottomColors, trafficBottomIndicator);
    }
    
    public void changeRectangleColor (ArrayList<Color> colorList, Rectangle indicator)
    {
        Collections.sort(colorList);
        
        switch (colorList.get(0)) {
            case RED:
                indicator.setFill(new javafx.scene.paint.Color(1, 0, 0, 1));
                break;
            case ORANGE:
                indicator.setFill(new javafx.scene.paint.Color(1, 0.65, 0, 1));
                break;
            case GREEN:
                indicator.setFill(new javafx.scene.paint.Color(0, 0.5, 0, 1));
                break;
            default:
                break;
        }
    }
}
