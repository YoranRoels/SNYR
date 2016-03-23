package panels;

import controllers.DriveController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.RijModel;

/**
 *
 * @author Yoran
 */
public class AnchorDrive extends AnchorPane 
{
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;
    
    private RijModel model;
    
    public AnchorDrive(BorderPane root,RijModel model) {
        this.root = root;
        this.model=model;
    }
    
    public void create()
    {    
        try{
            FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("DrivePane.fxml"));
            loader.setRoot(this);
            loader.setController(new DriveController(root,model));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
        }
    }
}
