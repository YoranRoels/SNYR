/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author sande
 */
public class SwitchPanelCommand implements EventHandler<Event> {
    
    private final BorderPane borderpane;
    private final AnchorPane counterpane;

    public SwitchPanelCommand(BorderPane borderpane, AnchorPane counterpane) {
        this.borderpane = borderpane;
        this.counterpane = counterpane;
    }
    
    @Override
    public void handle(Event event) {
        borderpane.setCenter(counterpane);
        System.out.println("switch");
    }
}
