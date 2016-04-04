package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import models.DriveModel;

/**
 *
 * @author Yoran
 */
public class DriveController implements InvalidationListener
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
    private Button brakeButton;
    @FXML
    private Button steeringButton;
    @FXML
    private Button shiftingButton;
    @FXML
    private Button lookingButton;
    @FXML
    private Button parkingButton;
    @FXML
    private Button turningButton;
    @FXML
    private Button garageButton;
    @FXML
    private Button reverseButton;
    @FXML
    private Button steeringPracticeButton;
    @FXML
    private Button hillButton;
    @FXML
    private Button sittingButton;
    @FXML
    private Button clutchButton;
    
    @FXML
    private Button fotoButton;
    
    private Button[] buttons;
    
    
    private final DriveModel model;
    
    public void initialize(){
        buttons= new Button[]{brakeButton,clutchButton,garageButton,hillButton,lookingButton,parkingButton,reverseButton,shiftingButton,sittingButton,steeringButton,steeringPracticeButton,turningButton};
        
        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");
        
        System.out.println("DriveController"); 
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

    public DriveController(BorderPane root,DriveModel model) 
    {
        System.out.println("DriveController Aangemaakt"); 
        this.root = root;
        this.model=model;
        model.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
       updateColors();
               }
    
    
    public void updateColors(){
        
        /*kleur doorgeven dus weeer unselecten*/
        if(radioGroup.selectedToggleProperty().isNotNull().get()){ 
            radioGroup.getSelectedToggle().setSelected(false);
                }
        
        clutchButton.setStyle("-fx-background-color:"+model.getClutchColor());
        brakeButton.setStyle("-fx-background-color:"+model.getBrakingColor());
        garageButton.setStyle("-fx-background-color:"+model.getGarageColor());
        hillButton.setStyle("-fx-background-color:"+model.getHillColor());
        lookingButton.setStyle("-fx-background-color:"+model.getLookingColor());
        parkingButton.setStyle("-fx-background-color:"+model.getParkingColor());
        reverseButton.setStyle("-fx-background-color:"+model.getReverseColor());
        shiftingButton.setStyle("-fx-background-color:"+model.getShiftingColor());
        sittingButton.setStyle("-fx-background-color:"+model.getPostureColor());
        steeringButton.setStyle("-fx-background-color:"+model.getSteeringColor());
        steeringPracticeButton.setStyle("-fx-background-color:"+model.getSteeringPracticeColor());
        turningButton.setStyle("-fx-background-color:"+model.getTurningColor());
       
        
    }

}
