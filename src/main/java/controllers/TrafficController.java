package controllers;

import commands.ExclamationCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import models.TrafficModel;

/**
 *
 * @author Yoran
 */
public class TrafficController implements InvalidationListener
{
    @FXML
    private ToggleGroup radioGroup;
    
    @FXML
    private RadioButton red;
    @FXML
    private RadioButton orange;
    @FXML
    private RadioButton green;
    
    @FXML
    private Button priority;
    @FXML
    private Button sign;
    @FXML
    private Button speed;
    @FXML
    private Button distance;
    @FXML
    private Button overtaking;
    @FXML
    private Button crossing;
    @FXML
    private Button turningleft;
    @FXML
    private Button turningright;
    @FXML
    private Button indicators;
    @FXML
    private Button publicroad;
    
    @FXML
    private TextArea commentfield;
    
    @FXML
    private Button fotoButton;
    
    @FXML
    private Button exclamationMarkButton;
    
    @FXML
    private MenuButton actionMenuButton;
    
    private Button[] buttons;
    
    private BorderPane root;
    
    private final TrafficModel model;
    
   
    
    
    public void initialize(){
        System.out.println("DriveController"); 
        buttons = new Button[]{priority,sign,speed,distance,overtaking,crossing,turningleft,turningright,indicators,publicroad};
        
        
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
                model.setColorForTechniek(radioGroup.getSelectedToggle().getUserData().toString());
                        }
        });
        
        commentfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.setCommentForTechniek(newValue);
            }
        });
        exclamationMarkButton.setOnAction(new ExclamationCommand(model.getExclamationField(), commentfield));
       
        update();
        
    }

    public TrafficController(BorderPane root, TrafficModel model) 
    {
        System.out.println("DriveController Aangemaakt"); 
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
        commentfield.setText(model.getCommentForTechniek());
        
        /*pas na selectie van een knop mag comentaar aanpasbaar zijn*/
        if(!model.getId().isEmpty()){ 
        commentfield.setEditable(true);
        }
         /*kleur doorgeven dus weeer unselecten*/
        if(radioGroup.selectedToggleProperty().isNotNull().get()){ 
            radioGroup.getSelectedToggle().setSelected(false);
                }
        sign.setStyle("-fx-background-color:"+model.getSignColor());
        priority.setStyle("-fx-background-color:"+model.getPriorityColor());
        speed.setStyle("-fx-background-color:"+model.getSpeedColor());
        distance.setStyle("-fx-background-color:"+model.getDistanceColor());
        overtaking.setStyle("-fx-background-color:"+model.getOvertakingColor());
        crossing.setStyle("-fx-background-color:"+model.getCrossingColor());
        turningleft.setStyle("-fx-background-color:"+model.getTurningleftColor());
        turningright.setStyle("-fx-background-color:"+model.getTurningrightColor());
        indicators.setStyle("-fx-background-color:"+model.getIndicatorsColor());
        publicroad.setStyle("-fx-background-color:"+model.getPublicroadColor());
    }
    
    @Override
    public void invalidated(Observable observable) {
        update();
    }
}
