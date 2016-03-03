/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author sande
 */
public class SideController {
    
    
    @FXML
    private ToggleButton olie;
    
    private BorderPane root;
    
    public void initialize(){
        System.out.println("SideController");  
    }
    
    public SideController(BorderPane root)
    {
        this.root=root;
    }
}
