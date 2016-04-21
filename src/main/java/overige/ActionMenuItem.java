/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overige;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

/**
 *
 * @author sande
 */
public class ActionMenuItem extends MenuItem {

    
    public ActionMenuItem(String text,TextArea commentfield) {
        super(text);
        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /*de actionmenu tekst toevoegen aan het comentaar veld*/
                MenuItem source=(MenuItem) event.getSource();
                if(commentfield.getText().endsWith("\n")|| commentfield.getText().isEmpty()){
                    commentfield.setText(commentfield.getText()+source.getText()+":");
                }else{
                    commentfield.setText(commentfield.getText()+"\n"+source.getText()+":");
                }
                
            }
        });
        
    }
    
    
    
}
