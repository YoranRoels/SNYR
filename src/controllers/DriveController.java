package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Yoran
 */
public class DriveController 
{
    
    private BorderPane root;
    
    @FXML
    private ToggleGroup radioGroup;
    
    @FXML
    private Button brakingButton;
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
    
    
    public void initialize(){
        System.out.println("DriveController"); 
    }

    public DriveController(BorderPane root) 
    {
        System.out.println("DriveController Aangemaakt"); 
        this.root = root;
    }
}
