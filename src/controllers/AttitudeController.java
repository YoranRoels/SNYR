package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
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
    private TextArea opmerkingenVeld;
    
    private final BorderPane root;
    
    private final ObservableList<String> selectie = FXCollections.observableArrayList("Zenuwachtig","Concentractie",
            "Schrik","Asociaal","Verkeersgevaarlijk","Ongeduldig","Agressief rijgedrag","Inzet","Verstrooid","Eigenwijs");
    
    public void initialize(){
        System.out.println("AttitudeController");
        selectieListView.setItems(selectie);
        selectieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                opmerkingenVeld.setText(opmerkingenVeld.getText()+"\n"+newValue);
                           }
        });
        //keerTerugButton.setOnMouseClicked(new SwitchPanelCommand(root));
        opmerkingenVeld.setText("test");
       // opmerkingenVeld.set
    }

    public AttitudeController(BorderPane root) 
    {
        this.root = root;
        System.out.println("Attitude Aangemaakt"); 
    }
    
}
