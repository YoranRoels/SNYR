/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author sande
 */
public class ClickSwitchPanelCommand implements EventHandler<MouseEvent> {
    
    private final BorderPane borderpane;
    private final AnchorPane counterpane;

    public ClickSwitchPanelCommand(BorderPane borderpane, AnchorPane counterpane) {
        this.borderpane = borderpane;
        this.counterpane = counterpane;
    }
    
    

    @Override
    public void handle(MouseEvent event) {
            borderpane.setCenter(counterpane);
            System.out.println("klik");
           }
    
}
