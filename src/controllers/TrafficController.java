package controllers;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author Yoran
 */
public class TrafficController 
{
    
    private BorderPane root;
    
    public void initialize(){
        System.out.println("TrafficController"); 
    }

    public TrafficController(BorderPane root) 
    {
        System.out.println("TrafficController Aangemaakt"); 
        this.root = root;
    }
}
