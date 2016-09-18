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
public class ConfirmationDialogController {
    
    @FXML
    private Button accept;
    @FXML
    private Button decline;
    @FXML
    private Label message;
    
    private final String messageText;
    
    private ConfirmationDialogModel model;
    
    public ConfirmationDialogController(String messageText, ConfirmationDialogModel model) 
    {
        this.messageText = messageText;
        this.model = model;
    }
    
    public void initialize()
    {
        System.out.println("Asking confirmation.");
        
        message.setText(messageText);
        
        accept.setOnAction((ActionEvent event) -> {
            model.setAccepted(true);
            System.out.println("accept");
            Node  source = (Node)  event.getSource(); 
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });
        
        decline.setOnAction((ActionEvent event)->{
            model.setAccepted(false);
            System.out.println("decline");
            Node  source = (Node)  event.getSource(); 
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();
        });
    }
}
