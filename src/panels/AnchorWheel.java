/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import controllers.WheelController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author sande
 */
public class AnchorWheel extends AnchorPane {
    
    public void create(){
        
     try{
            FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("WheelPane.fxml"));
            loader.setRoot(this);
            loader.setController(new WheelController());
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }

    }
    
}
