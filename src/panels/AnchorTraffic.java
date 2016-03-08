package panels;

import controllers.DriveController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Yoran
 */
public class AnchorTraffic extends AnchorPane 
{
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;
    
    
    public AnchorTraffic(BorderPane root) {
        this.root = root;
    }
    
    public void create()
    {    
        try{
            FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("TrafficPane.fxml"));
            loader.setRoot(this);
            loader.setController(new DriveController(root));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
        }
    }
}
