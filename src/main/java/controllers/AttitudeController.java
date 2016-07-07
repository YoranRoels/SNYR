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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
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
    private ImageView attitudeTextFieldCorners;
    
    @FXML
    private Button closeTextArea;
    
    @FXML
    private Rectangle blueRect;
    
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
       
        opmerkingenVeld.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    animateMove(0, -94, opmerkingenVeld);
                    animateMove(0, -94, attitudeTextFieldCorners);
                    animateMove(0, -94, blueRect);
                    closeTextArea.setVisible(true);
                }
                else
                {
                    animateMove(-94, 0, opmerkingenVeld);
                    animateMove(-94, 0, attitudeTextFieldCorners);
                    animateMove(-94, 0, blueRect);
                    closeTextArea.setVisible(false);
                }
            }
            
        });
       
       attitudeTextFieldCorners.setMouseTransparent(true); 
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
