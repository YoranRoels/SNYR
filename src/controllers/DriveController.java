package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import models.RijModel;

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
    
    private final Button[] buttons = {brakeButton,clutchButton,garageButton,hillButton,
        lookingButton,parkingButton,reverseButton,shiftingButton,sittingButton,
        steeringButton,steeringPracticeButton,turningButton};
    
    
    private final RijModel model;
    
    public void initialize(){
        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");
        
        System.out.println("DriveController"); 
//        for(Button b : buttons){
//            System.out.println(b.getId());
////            b.setOnAction((value)->{
////                System.out.println("test");
////            });
//        }
    clutchButton.setOnAction((value)->{
        fotoButton.setId(clutchButton.getId());
        //System.out.println(fotoButton.getId());
    });
        radioGroup.selectedToggleProperty().addListener((listener)->{
            model.selecteerTechniek(radioGroup.getSelectedToggle().getUserData().toString(),fotoButton.getId());      
        });
    }

    public DriveController(BorderPane root,RijModel model) 
    {
        System.out.println("DriveController Aangemaakt"); 
        this.root = root;
        this.model=model;
        model.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
