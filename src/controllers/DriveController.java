package controllers;

import javafx.fxml.FXML;
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
    
    public void initialize(){
        System.out.println("DriveController"); 
    }

    public DriveController(BorderPane root) 
    {
        System.out.println("DriveController Aangemaakt"); 
        this.root = root;
    }
}
