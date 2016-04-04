package panels;

import controllers.TrafficController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.TrafficModel;

/**
 *
 * @author Yoran
 */
public class AnchorTraffic extends AnchorPane 
{
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;
    
    private final TrafficModel model;
    
    public AnchorTraffic(BorderPane root,TrafficModel model) {
        this.root = root;
        this.model=model;
    }
    
    public void create()
    {    
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/TrafficPane.fxml"));
            loader.setRoot(this);
            loader.setController(new TrafficController(root,model));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex); 
        }
    }
}
