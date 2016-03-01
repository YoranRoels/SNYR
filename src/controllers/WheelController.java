/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 *
 * @author sande
 */
public class WheelController {
    
    @FXML
    private Button driveButton;
    @FXML
    private Button trafficButton;
    
    public void initialize(){
        System.out.println("wheelcontroller");
        
        driveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                driveButton.setText("It works!");
            }
        });
    }
}
