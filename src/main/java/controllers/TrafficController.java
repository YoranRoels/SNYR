package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
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
    private Button fotoButton;
    
    private Button[] buttons;
    
    private BorderPane root;
    
    private final TrafficModel model;
    
    
    public void initialize(){
        System.out.println("DriveController"); 
        buttons = new Button[]{priority,sign,speed,distance,overtaking,crossing,turningleft,turningright,indicators,publicroad};
        
        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");
        
        for(Button b : buttons){
            b.setOnAction((value)->{
                fotoButton.setId(b.getId());
                fotoButton.setStyle(b.getStyle());
            });
        }
        
        radioGroup.selectedToggleProperty().addListener((listener)->{
            if(radioGroup.selectedToggleProperty().isNotNull().get()){
                /*zet fotoButton op geselecteerde kleur*/
                fotoButton.setStyle("-fx-background-color:"+radioGroup.getSelectedToggle().getUserData());
                /*persisteren van de kleur*/
                model.setColorForTechniek(radioGroup.getSelectedToggle().getUserData().toString(),fotoButton.getId());
                        }
        });
        
        updateColors();
        
    }

    public TrafficController(BorderPane root, TrafficModel model) 
    {
        System.out.println("DriveController Aangemaakt"); 
        this.root = root;
        this.model=model;
    }

    public void updateColors(){
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
        updateColors();
    }
}
