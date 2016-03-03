package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Yoran
 */
public class DriveController 
{
    @FXML
    private RadioButton redRadioButton;
            
    @FXML
    private RadioButton orangeRadioButton;
            
    @FXML
    private RadioButton greenRadioButton;
    
    private BorderPane root;
    
    public void initialize(){
        System.out.println("DriveController"); 
    }

    public DriveController(BorderPane root) 
    {
        System.out.println("DriveController Aangemaakt"); 
        this.root = root;
    }
}
