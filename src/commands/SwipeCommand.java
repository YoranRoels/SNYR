/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author sande
 */
public class SwipeCommand implements EventHandler<ActionEvent> {
    
    private final BorderPane borderpane;
    private final AnchorPane counterpane;

    public SwipeCommand(BorderPane borderpane, AnchorPane counterpane) {
        this.borderpane = borderpane;
        this.counterpane = counterpane;
    }
    
    

    @Override
    public void handle(ActionEvent event) {
            borderpane.setCenter(counterpane);
           }
    
}
