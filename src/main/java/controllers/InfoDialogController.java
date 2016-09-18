/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.ConfirmationDialogModel;

/**
 *
 * @author Yoran
 */
public class InfoDialogController {
    
    @FXML
    private Button ok;
    @FXML
    private Label message;
    
    private final String messageText;
    
    public InfoDialogController(String messageText) 
    {
        this.messageText = messageText;
    }
    
    public void initialize()
    {
        System.out.println("Info showing.");
        
        message.setText(messageText);
        
        ok.setOnAction((ActionEvent event)->
        {
            System.out.println("ok");
            Node  source = (Node)  event.getSource(); 
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }
}
