/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import domein.Skills;
import domein.Student;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import models.SkillsModel;

/**
 *
 * @author sande
 */
public class SideController implements InvalidationListener{
    
    
    @FXML
    private ToggleButton oil;
    @FXML
    private ToggleButton tires;
    @FXML
    private ToggleButton gps;
    @FXML
    private ToggleButton roundabout;
    @FXML
    private ToggleButton doublelane;
    @FXML
    private ToggleButton lights;
    @FXML
    private ToggleButton fueling;
    @FXML
    private ToggleButton citytraffic;
    @FXML
    private ToggleButton highway;
    @FXML
    private ToggleButton emergencystop;
    
    private final BorderPane root;
    
    private final SkillsModel switchModel;
    
    public void initialize(){
        System.out.println("SideController");
        /*knoppen instellenn*/
        setButtons();
        oil.setOnAction((value)->{
            switchModel.switchOil();
        });
        fueling.setOnAction((value)->{
            switchModel.switchFueling();
        });
        citytraffic.setOnAction((value)->{
            switchModel.switchCityTraffic();
        });
        doublelane.setOnAction((value)->{
            switchModel.switchDoubleLaneRoad();
        });
        highway.setOnAction((value)->{
            switchModel.switchHighWay();
        });
        emergencystop.setOnAction((value)->{
            switchModel.switchEmergencyStop();
        });
        gps.setOnAction((value)->{
            switchModel.switchGps();
        });
        lights.setOnAction((value)->{
            switchModel.switchLights();
        });
        roundabout.setOnAction((value)->{
            switchModel.switchRoundAbout();
        });
        tires.setOnAction((value)->{
            switchModel.switchTires();
        });
    }
    
    public SideController(BorderPane root,SkillsModel switchmodel)
    {
        this.root=root;
        this.switchModel=switchmodel;
        switchmodel.addListener(this);
    }

    @Override
    public void invalidated(Observable observable) {
        /*reageren op verandering van evaluatienumber*/
        System.out.println("swtiche");
        setButtons();
           }
    
    
    public void setButtons(){
        oil.setSelected(switchModel.getOil());
        citytraffic.setSelected(switchModel.getCityTraffic());
        doublelane.setSelected(switchModel.getDoubleLaneRoad());
        emergencystop.setSelected(switchModel.getEmergencyStop());
        fueling.setSelected(switchModel.getFueling());
        gps.setSelected(switchModel.getGps());
        highway.setSelected(switchModel.getHighWay());
        lights.setSelected(switchModel.getLights());
        roundabout.setSelected(switchModel.getRoundAbout());
        tires.setSelected(switchModel.getTires());
    }
}
