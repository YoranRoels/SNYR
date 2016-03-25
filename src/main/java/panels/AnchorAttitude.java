package main.java.panels;

import main.java.controllers.AttitudeController;
import main.java.controllers.DriveController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Yoran
 */
public class AnchorAttitude extends AnchorPane 
{
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;
    
    
    public AnchorAttitude(BorderPane root) {
        this.root = root;
    }
    
    public void create()
    {    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/AttitudePane.fxml"));
            loader.setRoot(this);
            loader.setController(new AttitudeController(root));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
        }
    }
}
