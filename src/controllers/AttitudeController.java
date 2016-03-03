package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ListView<String> selectieListView;
    
    @FXML
    private TextField opmerkingenVeld;
    
    private final BorderPane root;
    
    private final ObservableList<String> selectie = FXCollections.observableArrayList("Zenuwachtig","Concentractie");
    
    public void initialize(){
        System.out.println("AttitudeController");
        selectieListView.setItems(selectie);
        //keerTerugButton.setOnMouseClicked(new SwitchPanelCommand(root));
        opmerkingenVeld.setText("test");
    }

    public AttitudeController(BorderPane root) 
    {
        this.root = root;
        System.out.println("Attitude Aangemaakt"); 
    }
    
}
