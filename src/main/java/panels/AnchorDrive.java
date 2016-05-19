package panels;

import controllers.DriveController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.DriveModel;

/**
 *
 * @author Yoran
 */
public class AnchorDrive extends AnchorPane 
{
    private final BorderPane root;
    
    private DriveModel model;
    
    public AnchorDrive(BorderPane root,DriveModel model) {
        this.root = root;
        this.model=model;
    }
    
    public void create()
    {    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/DrivePane.fxml"));
            loader.setRoot(this);
            loader.setController(new DriveController(root,model));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
        }
    }
}
