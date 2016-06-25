package controllers;

import commands.ExclamationCommand;
import domein.Color;
import java.util.ArrayList;
import java.util.Collections;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import models.TrafficModel;

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

    private BorderPane root;

    private final TrafficModel model;

    public void initialize() {
        System.out.println("DriveController");
        buttons = new Button[]{priority, sign, speed, distance, overtaking, crossing, turningleft, turningright, indicators, publicroad};

        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");

        commentfield.setEditable(false);
        commentfield.setDisable(true);

        for (Button b : buttons) {
            b.setOnAction((value) -> {
                model.setIdEnStyle(b.getId(), b.getStyle());
            });
        }

        radioGroup.selectedToggleProperty().addListener((listener) -> {
            /*pas iets doen als niet null, en er een techniek is geslecteerd*/
            if (radioGroup.selectedToggleProperty().isNotNull().get() && !model.getId().isEmpty()) {
                /*persisteren van de kleur*/
                model.setColorForTechniek(radioGroup.getSelectedToggle().getUserData().toString());
            }
        });

        closeTextArea.setVisible(false);

        commentfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                model.setCommentForTechniek(newValue);
            }
        });
        exclamationMarkButton.setOnAction(new ExclamationCommand(model.getExclamationField(), commentfield));

        update();

        commentfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    animateMove(0, -171);
                    closeTextArea.setVisible(true);
                } else {
                    animateMove(-171, 0);
                    closeTextArea.setVisible(false);
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
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), commentfield);
        translateTransition.setFromY(from);
        translateTransition.setToY(to);
        translateTransition.play();
    }
}
