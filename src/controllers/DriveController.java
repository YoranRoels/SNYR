package controllers;

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
        this.root = root;
        System.out.println("DriveController Aangemaakt"); 
    }
}
