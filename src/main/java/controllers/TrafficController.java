package controllers;

import commands.ExclamationCommand;
import domein.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.TrafficModel;
import overige.ActionMenuItem;

/**
 *
 * @author Yoran
 */
public class TrafficController implements InvalidationListener {

    @FXML
    private ToggleGroup radioGroup;

    @FXML
    private RadioButton red;
    @FXML
    private RadioButton orange;
    @FXML
    private RadioButton green;

    @FXML
    private Button priority;
    @FXML
    private Button sign;
    @FXML
    private Button speed;
    @FXML
    private Button distance;
    @FXML
    private Button overtaking;
    @FXML
    private Button crossing;
    @FXML
    private Button turningleft;
    @FXML
    private Button turningright;
    @FXML
    private Button indicators;
    @FXML
    private Button publicroad;
    @FXML
    private TextArea commentfield;
    @FXML
    private Label commentLabel;
    @FXML
    private Button closeTextArea;
    @FXML
    private Button fotoButton;
    @FXML
    private Button exclamationMarkButton;
    @FXML
    private MenuButton actionMenuButton;
    @FXML
    private Rectangle trafficLeftIndicatorInPane;
    @FXML
    private Rectangle trafficRightIndicatorInPane;
    @FXML
    private Rectangle trafficBottomIndicatorInPane;

    private Button[] buttons;
    
    private final HashMap<String, List<MenuItem>> menuitems = new HashMap<>();

    private BorderPane root;

    private final TrafficModel model;

    public void initialize() {
        System.out.println("DriveController");
        buttons = new Button[]{priority, sign, speed, distance, overtaking, crossing, turningleft, turningright, indicators, publicroad};

        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");
        
        red.setDisable(true);
        orange.setDisable(true);
        green.setDisable(true);
        
        fotoButton.setDisable(true);
        commentLabel.setDisable(true);
        
        actionMenuButton.getItems().clear();

        menuitems.put("turningleft", new ArrayList<>(Arrays.asList(new ActionMenuItem("Breder", commentfield), new ActionMenuItem("Kijktechniek", commentfield), new ActionMenuItem("Voorrang tegenliggers", commentfield))));
        menuitems.put("turningright", new ArrayList<>(Arrays.asList(new ActionMenuItem("Korter", commentfield), new ActionMenuItem("Kijktechniek", commentfield), new ActionMenuItem("Voorrang fietsers", commentfield))));
        menuitems.put("distance", new ArrayList<>(Arrays.asList(new ActionMenuItem("Rakelings", commentfield), new ActionMenuItem("Te dicht op voorligger", commentfield))));
        menuitems.put("publicroad", new ArrayList<>(Arrays.asList(new ActionMenuItem("Meer rechts", commentfield), new ActionMenuItem("Te rechts", commentfield), new ActionMenuItem("Juiste rijstrook", commentfield), new ActionMenuItem("Bochtentechniek", commentfield), new ActionMenuItem("Kijktechniek", commentfield))));
        menuitems.put("priority", new ArrayList<>(Arrays.asList(new ActionMenuItem("Voorrang aan rechts", commentfield), new ActionMenuItem("Voorrang aan links en rechts", commentfield), new ActionMenuItem("Voorrang aan anderen", commentfield), new ActionMenuItem("Voorrang nemen", commentfield), new ActionMenuItem("Meer vergewissen", commentfield), new ActionMenuItem("Aangepaste snelheid", commentfield))));
        menuitems.put("sign", new ArrayList<>(Arrays.asList(new ActionMenuItem("Verkeersborden", commentfield), new ActionMenuItem("Verkeerslichten", commentfield), new ActionMenuItem("Wegmarkeringen", commentfield), new ActionMenuItem("Meer vergewissen", commentfield))));
        menuitems.put("speed", new ArrayList<>(Arrays.asList(new ActionMenuItem("Niet aangepaste snelheid", commentfield), new ActionMenuItem("Te snel", commentfield), new ActionMenuItem("Te traag", commentfield), new ActionMenuItem("Vlotter", commentfield))));
        menuitems.put("indicators", new ArrayList<>(Arrays.asList(new ActionMenuItem("Vroeger", commentfield), new ActionMenuItem("Meer", commentfield))));
        menuitems.put("crossing", new ArrayList<>(Arrays.asList(new ActionMenuItem("Kijktechniek", commentfield), new ActionMenuItem("Koers houden", commentfield))));
        menuitems.put("overtaking", new ArrayList<>(Arrays.asList(new ActionMenuItem("Te dicht", commentfield), new ActionMenuItem("Voorbereiding", commentfield), new ActionMenuItem("Uitvoering", commentfield), new ActionMenuItem("Kijktechniek", commentfield))));
       
        actionMenuButton.setDisable(true);
        
        for (Button b : buttons) {
            b.setOnAction((value) -> {
                model.setIdEnStyle(b.getId(), b.getStyle());
                actionMenuButton.getItems().setAll(menuitems.get(b.getId()));
                actionMenuButton.setDisable(false);
            });
        }

        radioGroup.selectedToggleProperty().addListener((listener) -> {
            /*pas iets doen als niet null, en er een techniek is geslecteerd*/
            if (radioGroup.selectedToggleProperty().isNotNull().get() && !model.getId().isEmpty()) {
                /*persisteren van de kleur*/
                model.setColorForTechniek(radioGroup.getSelectedToggle().getUserData().toString());
            }
        });

        commentfield.setEditable(false);
        commentfield.setDisable(true);
        closeTextArea.setVisible(false);

        commentfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.setCommentForTechniek(newValue);
            }
        });
        
        exclamationMarkButton.setOnAction(new ExclamationCommand(model.getExclamationField(), commentfield));
        exclamationMarkButton.setDisable(true);

        update();

        commentfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    animateMove(0, -171);
                } else {
                    animateMove(-171, 0);
                }
            }
        });
    }

    public TrafficController(BorderPane root, TrafficModel model) {
        this.root = root;
        this.model = model;
        model.addListener(this);
    }

    public void update() {

        /*foto button instellen*/
        fotoButton.setId(model.getId());
        fotoButton.setStyle(model.getStyle());

        /*comment invullen*/
        commentfield.setText(model.getCommentForTechniek());

        /*pas na selectie van een knop mag comentaar aanpasbaar zijn*/
        if (!model.getId().isEmpty()) {
            commentfield.setEditable(true);
            commentfield.setDisable(false);
            red.setDisable(false);
            orange.setDisable(false);
            green.setDisable(false);
            exclamationMarkButton.setDisable(false);
            fotoButton.setDisable(false);
            commentLabel.setDisable(false);
            actionMenuButton.setDisable(false);
        }
        /*kleur doorgeven dus weeer unselecten*/
        if (radioGroup.selectedToggleProperty().isNotNull().get()) {
            radioGroup.getSelectedToggle().setSelected(false);
        }

        sign.setStyle("-fx-background-color:" + model.getSignColor());
        priority.setStyle("-fx-background-color:" + model.getPriorityColor());
        speed.setStyle("-fx-background-color:" + model.getSpeedColor());
        distance.setStyle("-fx-background-color:" + model.getDistanceColor());
        overtaking.setStyle("-fx-background-color:" + model.getOvertakingColor());
        crossing.setStyle("-fx-background-color:" + model.getCrossingColor());
        turningleft.setStyle("-fx-background-color:" + model.getTurningleftColor());
        turningright.setStyle("-fx-background-color:" + model.getTurningrightColor());
        indicators.setStyle("-fx-background-color:" + model.getIndicatorsColor());
        publicroad.setStyle("-fx-background-color:" + model.getPublicroadColor());

        ArrayList<Color> trafficLeftColors = new ArrayList<>();
        trafficLeftColors.add(model.getIndicatorsColor());
        trafficLeftColors.add(model.getCrossingColor());
        trafficLeftColors.add(model.getOvertakingColor());

        changeRectangleColor(trafficLeftColors, trafficLeftIndicatorInPane);

        ArrayList<Color> trafficRightColors = new ArrayList<>();
        trafficRightColors.add(model.getPublicroadColor());
        trafficRightColors.add(model.getTurningleftColor());
        trafficRightColors.add(model.getTurningrightColor());
        trafficRightColors.add(model.getDistanceColor());

        changeRectangleColor(trafficRightColors, trafficRightIndicatorInPane);

        ArrayList<Color> trafficBottomColors = new ArrayList<>();
        trafficBottomColors.add(model.getPriorityColor());
        trafficBottomColors.add(model.getSignColor());
        trafficBottomColors.add(model.getSpeedColor());

        changeRectangleColor(trafficBottomColors, trafficBottomIndicatorInPane);
    }

    @Override
    public void invalidated(Observable observable) {
        update();
    }

    public void changeRectangleColor(ArrayList<Color> colorList, Rectangle indicator) {
        Collections.sort(colorList);

        switch (colorList.get(0)) {
            case RED:
                indicator.setFill(new javafx.scene.paint.Color(1, 0, 0, 1));
                break;
            case ORANGE:
                indicator.setFill(new javafx.scene.paint.Color(1, 0.65, 0, 1));
                break;
            case GREEN:
                indicator.setFill(new javafx.scene.paint.Color(0, 0.5, 0, 1));
                break;
            default:
                break;
        }
    }
    
    public void animateMove(int from, int to)
    {
        if(to == 0)
        {
            closeTextArea.setVisible(false);
        }
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(400), commentfield);
        translateTransition.setFromY(from);
        translateTransition.setToY(to);
        translateTransition.play();
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                if(from == 0)
                {
                    closeTextArea.setVisible(true);
                }
            }
        });
    }
}
