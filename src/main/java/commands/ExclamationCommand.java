/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commands;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

/**
 *
 * @author sande
 */
public class ExclamationCommand implements EventHandler<ActionEvent> {
    
    private final TextArea exclamationField;
    private final TextArea commentaarveld;
    
    public ExclamationCommand(TextArea exclamationField,TextArea commentaarveld){
        this.exclamationField=exclamationField;
        this.commentaarveld=commentaarveld;
    }

    @Override
    public void handle(ActionEvent event) 
    {
        if(!commentaarveld.getText().isEmpty())
        {
            if(exclamationField == null)
            {
                System.out.println("! = null");
            }
            if(commentaarveld == null)
            {
                System.out.println("Commentaarveld = null");
            }
            String timeStamp = new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(Calendar.getInstance().getTime());
            exclamationField.setText(exclamationField.getText()+timeStamp+": "+commentaarveld.getText()+"\n");
        }
    }
    
}
