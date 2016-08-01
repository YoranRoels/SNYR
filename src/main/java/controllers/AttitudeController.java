package controllers;

import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
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
    
    @FXML
    private Button closeTextArea;
    
    @FXML
    private Pane borderedTextAreaPane;
    
    @FXML
    private Label attitudeLabel;
    
    private final BorderPane root;
    
    private final AttitudeModel model;
            
    public void initialize()
    {   
        closeTextArea.setVisible(false);
        System.out.println("AttitudeController");
        selectieListView.setItems(model.getSelectie());
        selectieListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                opmerkingenVeld.setText(opmerkingenVeld.getText() + "\n" + newValue);
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
                if(!toevoegField.getText().isEmpty())
                {
                    model.addNieuwSelectieWoord(toevoegField.getText());
                    toevoegField.setText("");
                }
            }
        });
       
        opmerkingenVeld.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    animateMove(0, -94, borderedTextAreaPane);
                    animateMove(0, -94, attitudeLabel);
//                    animateMove(0, -94, root);
                    closeTextArea.setVisible(true);
                }
                else
                {
                    animateMove(-94, 0, borderedTextAreaPane);
                    animateMove(-94, 0, attitudeLabel);
//                    animateMove(-94, 0, root);
                    closeTextArea.setVisible(false);
                }
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
    
    public void animateMove(int from, int to, Node node)
    {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        translateTransition.setFromY(from);
        translateTransition.setToY(to);
        translateTransition.play();
    }
}
