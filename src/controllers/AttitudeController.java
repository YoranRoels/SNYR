package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Yoran
 */
public class AttitudeController 
{
    @FXML
    private Button keerTerugButton;
    
    @FXML
    private ListView selectieListView;
    
    @FXML
    private TextField opmerkingenVeld;
    
    private final BorderPane root;
    
    public void initialize(){
        System.out.println("AttitudeController");
        
        //keerTerugButton.setOnMouseClicked(new SwitchPanelCommand(root));
    }

    public AttitudeController(BorderPane root) 
    {
        this.root = root;
        System.out.println("Attitude Aangemaakt"); 
    }
    
}
