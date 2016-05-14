/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import commands.SwitchPanelCommand;
import domein.Student;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.AttitudeModel;
import models.DriveModel;
import models.HomeModel;
import models.Model;
import models.SkillsModel;
import models.TrafficModel;
import panels.AnchorSide;
import panels.AnchorWheel;



/**
 *
 * @author sande
 */
public class HomeController implements InvalidationListener{
    
    @FXML
    private ImageView profielFotoBorders; 
    // Zwarte randen (profielFotoImage is de echte afbeelding).
    @FXML
    private ImageView profielFotoImage;
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
    private ImageView trashcan;
    
    @FXML
    private TextArea exclamationField;
    @FXML // Zwarte randen
    private ImageView commentBoxBorders;
    
    
    private AnchorWheel wheelpane;
    
    private AnchorSide sidepane;
    
    private final Stage stage;
    
    private final SkillsModel skillModel;
    
    private final DriveModel driveModel;
    
    private final TrafficModel trafficModel;
    
    private final AttitudeModel attitudeModel;
    
    private final HomeModel homeModel;
    
    private Model[] models;
    
    private final Student student;
    
    private final InlogController ic;
    

    public HomeController(Stage stage,Student student,InlogController ic,ObservableList<String> selectie) {
        this.skillModel = new SkillsModel(student);
        this.driveModel=new DriveModel(student);
        this.trafficModel=new TrafficModel(student);
        
        this.attitudeModel=new AttitudeModel(student,selectie);
        this.homeModel=new HomeModel(student);
        models=new Model[]{skillModel,driveModel,trafficModel,attitudeModel,homeModel};
        this.stage = stage;
        this.student=student;
        this.ic=ic;
        homeModel.addListener(this);
        
    }
    
    
    
    public void initialize(){
    //main setup initialize van de gui
        System.out.println("Start initliaze");
        skillModel.setExclamationField(exclamationField);
        driveModel.setExclamationField(exclamationField);
        trafficModel.setExclamationField(exclamationField);
        
        wheelpane=new AnchorWheel(borderpane,driveModel,trafficModel,attitudeModel);
        sidepane=new AnchorSide(borderpane,skillModel);

        wheelpane.setAnchorSide(sidepane);
        sidepane.setAnchorWheel(wheelpane);
        
        sidepane.create();
        wheelpane.create();
        
        borderpane.setCenter(wheelpane);
  
        //set profil pic van de user
        //profielFoto.setImage(null);
        
        /*klikken op de profiel foto brengt je naar de home/inlog page*/
        profielFotoImage.setOnMouseClicked((MouseEvent event) -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));
                
                ic.updateStudent(student);
                loader.setController(ic);
                Parent root = (Parent) loader.load();
                
                
               // Scene scene = new Scene(root);
               // stage.setScene(scene);
               stage.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
                    });
        /*terugknop brengt je naar de student homepage met sturen */
        terugknop.setOnMouseClicked(new SwitchPanelCommand(borderpane, wheelpane));
        
        commentBoxBorders.setMouseTransparent(true); // klik door de randen zodat je het textvak nog kan bedienen.
        profielFotoBorders.setMouseTransparent(true);
        
        
        //eva selecties in stellen en huidige eva aanzetten
        switch(student.getEvanumber()){
            case 0: evatoggle1.setSelected(true);
            break;
            case 1: evatoggle2.setSelected(true);
            break;
            case 2: evatoggle3.setSelected(true);
            break;
        }
        evatoggle1.setOnAction((ActionEvent event) -> {
            System.out.println("choose evalutation 1");
            veranderenEvaluatie(0);
            checkMinimalValue();
        });
        evatoggle2.setOnAction((ActionEvent event) -> {
            System.out.println("choose evalutation 2");
            veranderenEvaluatie(1);
            checkMinimalValue();
        });
        evatoggle3.setOnAction((ActionEvent event) -> {
            System.out.println("choose evalutation 3");
            veranderenEvaluatie(2);
            checkMinimalValue();
        });
        
        minusButton.setOnAction((ActionEvent event) -> {
            progressBar.progressProperty().setValue(progressBar.progressProperty().doubleValue()-0.05);
            homeModel.setProgres(progressBar.progressProperty().getValue());
            checkMinimalValue();
            updateSliderComment();
        });
        
        plusButton.setOnAction((ActionEvent event) -> {
            progressBar.progressProperty().setValue(progressBar.progressProperty().doubleValue()+0.05);
            homeModel.setProgres(progressBar.progressProperty().getValue());
            updateSliderComment();
        });
        
        trashcan.setOnMouseClicked((MouseEvent event) -> {
            exclamationField.setText("");
        });
    }
    public void updateSliderComment()
    {
        if(progressBar.progressProperty().doubleValue() < 0.2)
        {
            sliderLabel.setText("Student heeft nog veel bij te leren.");
        }
        else if(progressBar.progressProperty().doubleValue() > 0.2 && progressBar.progressProperty().doubleValue() < 0.7)
        {
            sliderLabel.setText("Klaar om met een begeleider te oefenen in de stageperiode.");
        }
        else if(progressBar.progressProperty().doubleValue() > 0.7 && progressBar.progressProperty().doubleValue() < 0.95)
        {
            sliderLabel.setText("Klaar om alleen te oefenen in de stageperiode.");
        }
        else if(progressBar.progressProperty().doubleValue() > 0.95)
        {
            sliderLabel.setText("Klaar voor praktisch examen.");
        }
    }
    
    public void checkMinimalValue()
    {
        if(progressBar.progressProperty().doubleValue() < 0)
        {
            System.out.println("Reached minimal value.");
            progressBar.progressProperty().setValue(0);
        }
    }
    
    @FXML
    protected void swipeCenterPanel(){
        System.out.println("test");
        
    }
    
    public void veranderenEvaluatie(int evanummer){
        student.setEvanumber(evanummer);
        for(Model m:models){
            m.EvaNumberChanged();
        }
//        attitudeModel.EvaNumberChanged();
//        driveModel.EvaNumberChanged();
//        trafficModel.EvaNumberChanged();
        /*skillmodel nog*/
        
    }

    @Override
    public void invalidated(Observable observable) {
       progressBar.setProgress(homeModel.getProgres());
    }
    
}
