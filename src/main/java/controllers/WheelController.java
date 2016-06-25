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
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.AttitudeModel;
import models.DriveModel;
import models.SkillsModel;
import models.TrafficModel;
import panels.AnchorSide;

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
    private Button attitudeButton;
    @FXML
    private Button skillButton;
    @FXML
    private Button comingSoonButton;
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
    private Label drivePaneLabel;
    @FXML
    private Rectangle drivePaneOpaque;
    @FXML
    private Label attitudePaneLabel;
    @FXML
    private Rectangle attitudePaneOpaque;
    @FXML
    private Label trafficPaneLabel;
    @FXML
    private Rectangle trafficPaneOpaque;
    @FXML
    private Label skillPaneLabel;
    @FXML
    private Rectangle skillPaneOpaque;
    @FXML
    private Label comingSoonLabel;
    @FXML
    private Rectangle comingSoonOpaque;
    
    
    private final BorderPane root;
    
    private AnchorDrive drivePane;
    private AnchorAttitude attitudePane;
    private AnchorTraffic trafficPane;
    private AnchorSide sidePane;
    
    private final DriveModel driveModel;
    private final TrafficModel trafficModel;
    private final AttitudeModel attitudeModel;
    private final SkillsModel skillsModel;
    
    public void initialize(){
        System.out.println("Wheelcontroller");
        update();
        drivePaneLabel.setMouseTransparent(true);
        drivePaneOpaque.setMouseTransparent(true);
        attitudePaneLabel.setMouseTransparent(true);
        attitudePaneOpaque.setMouseTransparent(true);
        trafficPaneLabel.setMouseTransparent(true);
        trafficPaneOpaque.setMouseTransparent(true);
        skillPaneLabel.setMouseTransparent(true);
        skillPaneOpaque.setMouseTransparent(true);
        
        startAnimations();
        
        drivePane = new AnchorDrive(root,driveModel);
        drivePane.create();
        driveButton.setOnMouseClicked(new SwitchPanelCommand(root, drivePane));
        
        attitudePane= new AnchorAttitude(root,attitudeModel);
        attitudePane.create();
        attitudeButton.setOnMouseClicked(new SwitchPanelCommand(root, attitudePane));
        
        trafficPane = new AnchorTraffic(root,trafficModel);
        trafficPane.create();
        trafficButton.setOnMouseClicked(new SwitchPanelCommand(root, trafficPane));
        
        sidePane = new AnchorSide(root,skillsModel);
        sidePane.create();
        skillButton.setOnMouseClicked(new SwitchPanelCommand(root, sidePane));
        
        driveModel.addListener(this); // kleuren in stuur aanpassen
        trafficModel.addListener(this); // kleuren in bord aanpassen
    }
    
    public WheelController(BorderPane root,DriveModel driveModel,TrafficModel trafficModel,AttitudeModel attitudeModel,SkillsModel skillsModel)
    {
       this.root=root;
       this.driveModel=driveModel;
       this.trafficModel=trafficModel;
       this.attitudeModel=attitudeModel;
       this.skillsModel=skillsModel;
    }

    @Override
    public void invalidated(Observable observable) {
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
    
    public void startAnimations()
    {
        //Labels
        animatePulse(drivePaneLabel);
        animatePulse(attitudePaneLabel);
        animatePulse(trafficPaneLabel);
        animatePulse(skillPaneLabel);
        animatePulse(comingSoonLabel);
        // Opaque
        animatePulse(drivePaneOpaque);
        animatePulse(attitudePaneOpaque);
        animatePulse(trafficPaneOpaque);
        animatePulse(skillPaneOpaque);
        animatePulse(comingSoonOpaque);
        // Buttons (circle border)
        animatePulse(driveButton);
        animatePulse(attitudeButton);
        animatePulse(trafficButton);
        animatePulse(skillButton);
        animatePulse(comingSoonButton);
    }
    
    public void animatePulse(Node node)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(1500), node);
        ft.setFromValue(0.6);
        ft.setToValue(1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
}
