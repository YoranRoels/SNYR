package panels;

import controllers.AttitudeController;
import controllers.DriveController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.AttitudeModel;

/**
 *
 * @author Yoran
 */
public class AnchorAttitude extends AnchorPane 
{
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;
    
    private final AttitudeModel model;
    
    public AnchorAttitude(BorderPane root,AttitudeModel model) {
        this.root = root;
        this.model=model;
    }
    
    public void create()
    {    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/AttitudePane.fxml"));
            loader.setRoot(this);
            loader.setController(new AttitudeController(root,model));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
        }
    }
}
