/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.ExclamationCommand;
import domein.Skills;
import domein.Student;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import models.SkillsModel;
import models.TrafficModel;

/**
 *
 * @author sande
 */
public class SideController implements InvalidationListener
{       
    private BorderPane root;
    
    @FXML
    private ToggleGroup radioGroup;
    
    @FXML
    private RadioButton red;
    @FXML
    private RadioButton orange;
    @FXML
    private RadioButton green;
    
    @FXML
    private Button tiresButton;
    @FXML
    private Button cityButton;
    @FXML
    private Button doublelaneButton;
    @FXML
    private Button gpsButton;
    @FXML
    private Button lightsButton;
    @FXML
    private Button emergencystopButton;
    @FXML
    private Button oilcheckButton;
    @FXML
    private Button roundaboutButton;
    @FXML
    private Button highwayButton;
    @FXML
    private Button fuelingButton;
    
    @FXML
    private Button fotoButton;
    
    @FXML
    private TextArea commentfield;
    
    @FXML
    private Button exclamationMarkButton;
    @FXML
    private Label skillPaneLabel;
    
    private Button[] buttons;
    
    private final SkillsModel model;
    
    public void initialize(){
        System.out.println("SideController"); 
        buttons = new Button[]{tiresButton,cityButton,doublelaneButton,gpsButton,lightsButton,emergencystopButton,oilcheckButton,roundaboutButton,highwayButton,fuelingButton};
        
        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");
        
        commentfield.setEditable(false);
        
        for(Button b : buttons){
            b.setOnAction((value)->{
                model.setIdEnStyle(b.getId(), b.getStyle());

            });
        }
        
        radioGroup.selectedToggleProperty().addListener((listener)->{
            /*pas iets doen als niet null, en er een techniek is geslecteerd*/
            if(radioGroup.selectedToggleProperty().isNotNull().get() && !model.getId().isEmpty()){
               /*persisteren van de kleur*/
                model.setColorForSkills(radioGroup.getSelectedToggle().getUserData().toString());
                        }
        });
        
        commentfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.setCommentForSkills(newValue);
            }
        });
        exclamationMarkButton.setOnAction(new ExclamationCommand(model.getExclamationField(), commentfield));
       
        animatePulse(skillPaneLabel);
        
        update();
    }

    public SideController(BorderPane root, SkillsModel model) 
    {
        this.root = root;
        this.model=model;
        model.addListener(this);
    }

    public void update(){
        
        /*foto button instellen*/
        fotoButton.setId(model.getId());
        fotoButton.setStyle(model.getStyle());
        if(radioGroup.selectedToggleProperty().isNotNull().get()){
        fotoButton.setStyle("-fx-background-color:"+radioGroup.getSelectedToggle().getUserData());
        }
        
        /*comment invullen*/
        commentfield.setText(model.getCommentForSkills());
        
        /*pas na selectie van een knop mag comentaar aanpasbaar zijn*/
        if(!model.getId().isEmpty()){ 
        commentfield.setEditable(true);
        }
         /*kleur doorgeven dus weeer unselecten*/
        if(radioGroup.selectedToggleProperty().isNotNull().get()){ 
            radioGroup.getSelectedToggle().setSelected(false);
                }
        tiresButton.setStyle("-fx-background-color:"+model.getTiresColor());
        cityButton.setStyle("-fx-background-color:"+model.getCityColor());
        doublelaneButton.setStyle("-fx-background-color:"+model.getDoublelaneColor());
        gpsButton.setStyle("-fx-background-color:"+model.getGpsColor());
        lightsButton.setStyle("-fx-background-color:"+model.getLightsColor());
        emergencystopButton.setStyle("-fx-background-color:"+model.getEmergencystopColor());
        oilcheckButton.setStyle("-fx-background-color:"+model.getOilcheckColor());
        roundaboutButton.setStyle("-fx-background-color:"+model.getRoundaboutColor());
        highwayButton.setStyle("-fx-background-color:"+model.getHighwayColor());
        fuelingButton.setStyle("-fx-background-color:"+model.getFuelingColor());
    }
    
    @Override
    public void invalidated(Observable observable) {
        update();
    }
    
    public void animatePulse(Label pulseLabel)
    {
        FadeTransition ft = new FadeTransition(Duration.millis(1000), pulseLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0.4);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
}
