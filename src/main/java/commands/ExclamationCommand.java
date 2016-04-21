/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author sande
 */
public class ExclamationCommand implements EventHandler<ActionEvent> {
    
    private final TextArea  exclamationField;
    private final TextArea  commentaarveld;
    
    public ExclamationCommand(TextArea  opmerkingveld,TextArea commentaarveld){
        this.exclamationField=opmerkingveld;
        this.commentaarveld=commentaarveld;
    }

    @Override
    public void handle(ActionEvent event) {
        exclamationField.setText(exclamationField.getText()+commentaarveld.getText()+"\n");
                
    }
    
}
