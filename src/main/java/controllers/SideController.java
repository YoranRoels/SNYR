/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.ExclamationCommand;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import models.SkillsModel;

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
    private Button closeTextArea;
    @FXML
    private Label commentLabel;

    private Button[] buttons;

    private final SkillsModel model;

    public void initialize(){
        System.out.println("SideController");
        buttons = new Button[]{tiresButton, cityButton, doublelaneButton, gpsButton, lightsButton, emergencystopButton, oilcheckButton, roundaboutButton, highwayButton, fuelingButton};

        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");
        
        red.setDisable(true);
        orange.setDisable(true);
        green.setDisable(true);
        
        fotoButton.setDisable(true);
        commentLabel.setDisable(true);

        commentfield.setEditable(false);

        for (Button b : buttons) {
            b.setOnAction((value) -> {
                model.setIdEnStyle(b.getId(), b.getStyle());
            });
        }

        radioGroup.selectedToggleProperty().addListener((listener) -> {
            /*pas iets doen als niet null, en er een techniek is geslecteerd*/
            if (radioGroup.selectedToggleProperty().isNotNull().get() && !model.getId().isEmpty()) {
                /*persisteren van de kleur*/
                model.setColorForSkills(radioGroup.getSelectedToggle().getUserData().toString());
            }
        });
        
        closeTextArea.setVisible(false);

        commentfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.setCommentForSkills(newValue);
            }
        });
        
        commentfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    animateMove(0, -171);
                } else {
                    animateMove(-171, 0);
                }
            }
        });
        
        exclamationMarkButton.setOnAction(new ExclamationCommand(model.getExclamationField(), commentfield));
        exclamationMarkButton.setDisable(true);
        
        update();
    }

    public SideController(BorderPane root, SkillsModel model) {
        this.root = root;
        this.model = model;
        model.addListener(this);
    }

    public void update() {

        /*foto button instellen*/
        fotoButton.setId(model.getId());
        fotoButton.setStyle(model.getStyle());
        if (radioGroup.selectedToggleProperty().isNotNull().get()) {
            fotoButton.setStyle("-fx-background-color:" + radioGroup.getSelectedToggle().getUserData());
        }

        /*comment invullen*/
        commentfield.setText(model.getCommentForSkills());

        /*pas na selectie van een knop mag comentaar aanpasbaar zijn*/
        if (!model.getId().isEmpty()) {
            commentfield.setEditable(true);
            commentfield.setDisable(false);
            red.setDisable(false);
            orange.setDisable(false);
            green.setDisable(false);
            exclamationMarkButton.setDisable(false);
            fotoButton.setDisable(false);
            commentLabel.setDisable(false);
        }
        /*kleur doorgeven dus weeer unselecten*/
        if (radioGroup.selectedToggleProperty().isNotNull().get()) {
            radioGroup.getSelectedToggle().setSelected(false);
        }
        
        tiresButton.setStyle("-fx-background-color:" + model.getTiresColor());
        cityButton.setStyle("-fx-background-color:" + model.getCityColor());
        doublelaneButton.setStyle("-fx-background-color:" + model.getDoublelaneColor());
        gpsButton.setStyle("-fx-background-color:" + model.getGpsColor());
        lightsButton.setStyle("-fx-background-color:" + model.getLightsColor());
        emergencystopButton.setStyle("-fx-background-color:" + model.getEmergencystopColor());
        oilcheckButton.setStyle("-fx-background-color:" + model.getOilcheckColor());
        roundaboutButton.setStyle("-fx-background-color:" + model.getRoundaboutColor());
        highwayButton.setStyle("-fx-background-color:" + model.getHighwayColor());
        fuelingButton.setStyle("-fx-background-color:" + model.getFuelingColor());
    }

    @Override
    public void invalidated(Observable observable) {
        update();
    }
    
    public void animateMove(int from, int to)
    {
        if(to == 0)
        {
            closeTextArea.setVisible(false);
        }
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), commentfield);
        translateTransition.setFromY(from);
        translateTransition.setToY(to);
        translateTransition.play();
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                if(from == 0)
                {
                    closeTextArea.setVisible(true);
                }
            }
        });
    }
}
