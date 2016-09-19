/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

//import async.SendMailTask;
import commands.SwitchPanelCommand;
import domein.Student;
//import java.awt.AWTException;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
//import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
//import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.AttitudeModel;
import models.DriveModel;
import models.HomeModel;
import models.Model;
import models.SkillsModel;
import models.TrafficModel;
import panels.AnchorWheel;

/**
 *
 * @author sande
 */
public class HomeController implements InvalidationListener {

    @FXML
    private ImageView logout;
    @FXML
    private RadioButton evatoggle1;
    @FXML
    private RadioButton evatoggle2;
    @FXML
    private RadioButton evatoggle3;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Button terugknop;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Label sliderLabel;
    @FXML
    private Button minusButton;
    @FXML
    private Button plusButton;
    @FXML
    private Button sliderDetailButton;
    private int sliderProgress = 0;
    @FXML
    private Label nameLabel;

    @FXML
    private TextArea exclamationField;

    private AnchorWheel wheelpane;

    private final Stage stage;

    private final SkillsModel skillModel;

    private final DriveModel driveModel;

    private final TrafficModel trafficModel;

    private final AttitudeModel attitudeModel;

    private final HomeModel homeModel;

    private Model[] models;

    private final Student student;

    private final InlogController inlogController;
    
    @FXML
    private Button reportButton;
    
//    private Robot robot;
    
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public HomeController(Stage stage, Student student, InlogController inlogController, ObservableList<String> selectie) {
        this.skillModel = new SkillsModel(student);
        this.driveModel = new DriveModel(student);
        this.trafficModel = new TrafficModel(student);
        this.attitudeModel = new AttitudeModel(student, selectie);
        this.homeModel = new HomeModel(student);
        models = new Model[]{driveModel, trafficModel, attitudeModel, homeModel, skillModel};
        this.stage = stage;
        this.student = student;
        this.inlogController = inlogController;
        homeModel.addListener(this);
    }

    public void initialize() {
        reportButton.setVisible(false); // Feature disabled (android incompatible)
        
        System.out.println("HomeController");
        skillModel.setExclamationField(exclamationField);
        driveModel.setExclamationField(exclamationField);
        trafficModel.setExclamationField(exclamationField);

        wheelpane = new AnchorWheel(borderpane, driveModel, trafficModel, attitudeModel, skillModel);
        wheelpane.create();

        borderpane.setCenter(wheelpane);
        
        /* The Logout icon will take you back to the studentScreen */
        logout.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));

                inlogController.updateStudent(student);
                loader.setController(inlogController);
                Parent root = (Parent) loader.load();

                // Scene scene = new Scene(root);
                // stage.setScene(scene);
                stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        /*The back button will put the wheelpane back in the center. */
        terugknop.setOnMouseClicked(new SwitchPanelCommand(borderpane, wheelpane));

        //eva selecties in stellen en huidige eva aanzetten
        switch (student.getEvanumber()) {
            case 0:
                evatoggle1.setSelected(true);
                break;
            case 1:
                evatoggle2.setSelected(true);
                break;
            case 2:
                evatoggle3.setSelected(true);
                break;
        }
        evatoggle1.setOnAction((ActionEvent event) -> {
            veranderenEvaluatie(0);
            checkMinimalValue();
        });
        evatoggle2.setOnAction((ActionEvent event) -> {
            veranderenEvaluatie(1);
            checkMinimalValue();
        });
        evatoggle3.setOnAction((ActionEvent event) -> {
            veranderenEvaluatie(2);
            checkMinimalValue();
        });

        minusButton.setOnAction((ActionEvent event) -> {
            progressBar.progressProperty().setValue(progressBar.progressProperty().doubleValue() - 0.142857142);
        });

        plusButton.setOnAction((ActionEvent event) -> {
            progressBar.progressProperty().setValue(progressBar.progressProperty().doubleValue() + 0.142857142);
        });
        
        nameLabel.setText(student.getAchternaam()+" "+student.getVoornaam());
        
        homeModel.setProgress(sliderProgress);
        
        sliderDetailButton.setOnAction((ActionEvent event) -> {
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/SliderDetail.fxml"));

                loader.setController(new SliderDetailController(homeModel));
                root = (Parent) loader.load();
                
                borderpane.setCenter(root);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        // When the progress property changes
        progressBar.progressProperty().addListener((listener) -> {
            keepValueInBounds();
            updateSliderComment();
            homeModel.setProgress(progressBar.progressProperty().getValue());
            System.out.println(homeModel.generatePictureNumber());
        });
        
//        reportButton.setOnAction((ActionEvent event) -> {
//            try 
//            {
//                robot = new Robot();
//            } 
//            catch (AWTException ex) 
//            {
//                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            
//            List<WritableImage> imagesToSend = new ArrayList<>();
//            borderpane.setCenter(wheelpane);
//            imagesToSend.add(captureScreen());
//            borderpane.setCenter(wheelpane);
//            imagesToSend.add(captureScreen());
//            borderpane.setCenter(wheelpane.getAnchorSide());
//            imagesToSend.add(captureScreen());
            
            
//            SendMailTask smt = new SendMailTask(student.getStudentnr(), imagesToSend);
//            service.submit(smt);
//        });
    }

    public void updateSliderComment() {
        if (progressBar.progressProperty().doubleValue() < 0.285714284) {
            sliderLabel.setText("");
        } else if (progressBar.progressProperty().doubleValue() >= 0.285714284 && progressBar.progressProperty().doubleValue() < 0.71428571) {
            sliderLabel.setText("Klaar om met een begeleider te oefenen in de stageperiode.");
        } else if (progressBar.progressProperty().doubleValue() >= 0.71428571 && progressBar.progressProperty().doubleValue() < 0.99) {
            sliderLabel.setText("Klaar om alleen te oefenen in de stageperiode.");
        } else if (progressBar.progressProperty().doubleValue() >= 0.99) {
            sliderLabel.setText("Klaar voor praktisch examen.");
        }
    }

    public void checkMinimalValue() {
        if (progressBar.progressProperty().doubleValue() < 0) {
            System.out.println("Reached minimal value.");
            progressBar.progressProperty().setValue(0);
        }
    }
    
    public void checkMaxValue() {
        if (progressBar.progressProperty().doubleValue() > 1) {
            System.out.println("Reached max value.");
            progressBar.progressProperty().setValue(1);
        }
    }
    
    public void keepValueInBounds()
    {
        checkMinimalValue();
        checkMaxValue();
    }

    public void veranderenEvaluatie(int evanummer) {
        student.changeEvanumber(evanummer);
        for (Model m : models) {
            m.EvaNumberChanged();
        }
//        attitudeModel.EvaNumberChanged();
//        driveModel.EvaNumberChanged();
//        trafficModel.EvaNumberChanged();
        /*skillmodel nog*/

    }
    
//    public WritableImage captureScreen()
//    {
//        Rectangle screenRect = new Rectangle((int)stage.getScene().getWidth(), (int)stage.getScene().getHeight());
//        BufferedImage image = robot.createScreenCapture(screenRect);
//        WritableImage wimg = new WritableImage((int)stage.getScene().getWidth(), (int)stage.getScene().getHeight());
//        SwingFXUtils.toFXImage(image, wimg);
//        return wimg;
//    }
    
    @Override
    public void invalidated(Observable observable) {
        progressBar.setProgress(homeModel.getProgress());
    }
}
