package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import models.AttitudeModel;

/**
 *
 * @author Yoran
 */
public class AttitudeController implements InvalidationListener
{
    @FXML
    private Button keerTerugButton;
    
    @FXML
    private ListView<String> selectieListView;
    
    @FXML
    private TextArea opmerkingenVeld;
    
    @FXML
    private TextField toevoegField;
    
    @FXML
    private Button toevoegButton;
    
    private final BorderPane root;
    
    private final AttitudeModel model;
            
    public void initialize()
    {   
        System.out.println("AttitudeController");
        selectieListView.setItems(model.getSelectie());
        selectieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                opmerkingenVeld.setText(opmerkingenVeld.getText()+"\n"+newValue);
                           }
        });
        
// opmerkingenVeld.set opmerkingen laden en setten
        opmerkingenVeld.setText(model.getAttitude());

       opmerkingenVeld.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    /*tekst opslaan als deze is veranderd*/
                    model.setAttitude(newValue);
            }
        });
       
       /*toevoeg button*/
       toevoegButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                model.addNieuwSelectieWoord(toevoegField.getText());
                toevoegField.setText("");
            }
        });
       
       
    }

    public AttitudeController(BorderPane root,AttitudeModel model) 
    {
        this.root = root;
        this.model = model;
        model.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
        opmerkingenVeld.setText(model.getAttitude());
            }
    
}
