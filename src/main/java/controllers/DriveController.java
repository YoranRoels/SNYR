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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Arc;
import javafx.util.Duration;
import models.DriveModel;
import overige.ActionMenuItem;

/**
 *
 * @author Yoran
 */
public class DriveController implements InvalidationListener {

    private BorderPane root;

    @FXML
    private ToggleGroup radioGroup;

    @FXML
    private RadioButton red;
    @FXML
    private RadioButton orange;
    @FXML
    private RadioButton green;
    @FXML
    private Button brakeButton;
    @FXML
    private Button steeringButton;
    @FXML
    private Button shiftingButton;
    @FXML
    private Button lookingButton;
    @FXML
    private Button parkingButton;
    @FXML
    private Button turningButton;
    @FXML
    private Button garageButton;
    @FXML
    private Button reverseButton;
    @FXML
    private Button steeringPracticeButton;
    @FXML
    private Button hillButton;
    @FXML
    private Button sittingButton;
    @FXML
    private Button clutchButton;
    @FXML
    private Button fotoButton;
    @FXML
    private Button exclamationMarkButton;
    @FXML
    private TextArea commentfield;
    @FXML
    private Button closeTextArea; // button to cancel focus on the commentfield to put it back into the normal position
    @FXML
    private MenuButton actionMenuButton;
    @FXML
    private Arc driveTopIndicatorInPane;
    @FXML
    private Arc driveLeftIndicatorInPane;
    @FXML
    private Arc driveRightIndicatorInPane;

    private Button[] buttons;

    private final HashMap<String, List<MenuItem>> menuitems = new HashMap<>();

    private final DriveModel model;

    public void initialize() {
        System.out.println("DriveController");
        buttons = new Button[]{brakeButton, clutchButton, garageButton, hillButton, lookingButton, parkingButton, reverseButton, shiftingButton, sittingButton, steeringButton, steeringPracticeButton, turningButton};
        red.setUserData("red");
        orange.setUserData("orange");
        green.setUserData("green");

        actionMenuButton.getItems().clear();

        menuitems.put("sitting", new ArrayList<>(Arrays.asList(new ActionMenuItem("zithouding", commentfield), new ActionMenuItem("Gordel", commentfield), new ActionMenuItem("Spiegels", commentfield), new ActionMenuItem("Handrem", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("clutch", new ArrayList<>(Arrays.asList(new ActionMenuItem("Dosering", commentfield), new ActionMenuItem("Volledig", commentfield), new ActionMenuItem("Plaatsing voet", commentfield), new ActionMenuItem("Onnodig", commentfield), new ActionMenuItem("Bocht", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("brake", new ArrayList<>(Arrays.asList(new ActionMenuItem("Dosering", commentfield), new ActionMenuItem("Volgorde", commentfield), new ActionMenuItem("Te laat", commentfield), new ActionMenuItem("Afremmen op de motor", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("steering", new ArrayList<>(Arrays.asList(new ActionMenuItem("Dosering", commentfield), new ActionMenuItem("Houding", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("shifting", new ArrayList<>(Arrays.asList(new ActionMenuItem("Bediening", commentfield), new ActionMenuItem("Aangepaste versnelling", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("looking", new ArrayList<>(Arrays.asList(new ActionMenuItem("Ver/dichtbij", commentfield), new ActionMenuItem("Meer vergewissen", commentfield), new ActionMenuItem("Spiegels", commentfield), new ActionMenuItem("Dode hoeken", commentfield), new ActionMenuItem("Scannen/selecteren", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("parking", new ArrayList<>(Arrays.asList(new ActionMenuItem("Tussen 2 voertuigen", commentfield), new ActionMenuItem("Achter een voertuig", commentfield), new ActionMenuItem("Links", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("turning", new ArrayList<>(Arrays.asList(new ActionMenuItem("Dosering", commentfield), new ActionMenuItem("Andere", commentfield))));
        menuitems.put("garage", new ArrayList<>(Arrays.asList(new ActionMenuItem("Andere", commentfield))));
        menuitems.put("reverse", new ArrayList<>(Arrays.asList(new ActionMenuItem("Andere", commentfield))));
        menuitems.put("steeringPractice", new ArrayList<>(Arrays.asList(new ActionMenuItem("Andere", commentfield))));
        menuitems.put("hill", new ArrayList<>(Arrays.asList(new ActionMenuItem("Balanceren", commentfield), new ActionMenuItem("Voetrem", commentfield), new ActionMenuItem("Handrem", commentfield), new ActionMenuItem("Andere", commentfield))));

        actionMenuButton.setDisable(true);

        for (Button b : buttons) {
            b.setOnAction((value) -> {
                model.setIdEnStyle(b.getId(), b.getStyle());
                actionMenuButton.getItems().setAll(menuitems.get(b.getId()));
                actionMenuButton.setDisable(false);
            });
        }

        commentfield.setEditable(false);
        commentfield.setDisable(true);

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
        
        commentfield.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue)
                {
                    animateMove(0, -171);
                    closeTextArea.setVisible(true);
                    
                }
                else
                {
                    animateMove(-171, 0);
                    closeTextArea.setVisible(false);
                }
            }
            
        });

        exclamationMarkButton.setOnAction(new ExclamationCommand(model.getExclamationField(), commentfield));

        update();
    }

    public DriveController(BorderPane root, DriveModel model) {
        this.root = root;
        this.model = model;
        model.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
        update();
    }

    public void update() {
        /*foto button instellen*/
        fotoButton.setId(model.getId());
        fotoButton.setStyle(model.getStyle());

        /*comment invullen*/
        commentfield.setText(model.getCommentForTechniek());

        /*pas na het selecteren van een knop mag de commentaar aanpasbaar zijn*/
        if (!model.getId().isEmpty()) {
            commentfield.setEditable(true);
            commentfield.setDisable(false);
        }

        /*kleur doorgeven dus weer unselecten*/
        if (radioGroup.selectedToggleProperty().isNotNull().get()) {
            radioGroup.getSelectedToggle().setSelected(false);
        }

        clutchButton.setStyle("-fx-background-color:" + model.getClutchColor());
        brakeButton.setStyle("-fx-background-color:" + model.getBrakingColor());
        garageButton.setStyle("-fx-background-color:" + model.getGarageColor());
        hillButton.setStyle("-fx-background-color:" + model.getHillColor());
        lookingButton.setStyle("-fx-background-color:" + model.getLookingColor());
        parkingButton.setStyle("-fx-background-color:" + model.getParkingColor());
        reverseButton.setStyle("-fx-background-color:" + model.getReverseColor());
        shiftingButton.setStyle("-fx-background-color:" + model.getShiftingColor());
        sittingButton.setStyle("-fx-background-color:" + model.getPostureColor());
        steeringButton.setStyle("-fx-background-color:" + model.getSteeringColor());
        steeringPracticeButton.setStyle("-fx-background-color:" + model.getSteeringPracticeColor());
        turningButton.setStyle("-fx-background-color:" + model.getTurningColor());

        ArrayList<Color> driveTopColors = new ArrayList<>();
        driveTopColors.add(model.getPostureColor());
        driveTopColors.add(model.getClutchColor());
        driveTopColors.add(model.getBrakingColor());
        driveTopColors.add(model.getSteeringColor());
        driveTopColors.add(model.getShiftingColor());
        driveTopColors.add(model.getLookingColor());

        changeRectangleColor(driveTopColors, driveTopIndicatorInPane);

        ArrayList<Color> driveLeftColors = new ArrayList<>();
        driveLeftColors.add(model.getHillColor());
        driveLeftColors.add(model.getSteeringPracticeColor());
        driveLeftColors.add(model.getReverseColor());
        driveLeftColors.add(model.getGarageColor());

        changeRectangleColor(driveLeftColors, driveLeftIndicatorInPane);

        ArrayList<Color> driveRightColors = new ArrayList<>();
        driveRightColors.add(model.getTurningColor());
        driveRightColors.add(model.getParkingColor());

        changeRectangleColor(driveRightColors, driveRightIndicatorInPane);
    }

    public void changeRectangleColor(ArrayList<Color> colorList, Arc indicator) {
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
