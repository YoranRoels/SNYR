/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import commands.SwipeCommand;
import controllers.SideController;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author sande
 */
public class AnchorSide extends AnchorPane{
    
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;

    public AnchorSide(BorderPane root) {
        this.root = root;
        
    }
    
    

    public AnchorWheel getAnchorWheel() {
        return anchorWheel;
    }

    public void setAnchorWheel(AnchorWheel anchorWheel) {
        this.anchorWheel = anchorWheel;
    }
    
    public void create(){
        
     try{
            FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("Sidepane.fxml"));
            loader.setRoot(this);
            loader.setController(new SideController());
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
            
        }
          this.setOnMouseClicked(new SwipeCommand(root,anchorWheel));


     

     
    }
    
}
