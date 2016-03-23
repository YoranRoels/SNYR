/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domein.Student;



/**
 *
 * @author sande
 * 
 * model voor alle switches in de sidePanels
 */
public class SkillsModel extends Model {
    
    private final Student student;
    
    public SkillsModel(Student student){
        this.student=student;
    }
    
    public void switchOil(){
       student.getCurrentEvalutie().setOilcheck( !getOil());
    }
    
    public boolean getOil(){
        return student.getCurrentEvalutie().isFueling();
    }
    
    public void switchTires(){
       student.getCurrentEvalutie().setTires(!getTires());
    }
    
    public boolean getTires(){
        return student.getCurrentEvalutie().isTires();
    }
    
    public void switchLights(){
      student.getCurrentEvalutie().setLights(!getLights());
    }
    public boolean getLights(){
        return student.getCurrentEvalutie().isLights();
    }
    
    public void switchRoundAbout(){
       student.getCurrentEvalutie().setRoundabout(!getRoundAbout());
    }
    public boolean getRoundAbout(){
        return student.getCurrentEvalutie().isRoundabout();
    }
    
    public void switchHighWay(){
       student.getCurrentEvalutie().setRoundabout(getHighWay());
    }
    public boolean getHighWay(){
        return student.getCurrentEvalutie().isHighway();
    }
    
    public void switchCityTraffic(){
       student.getCurrentEvalutie().setCitytraffic(!getCityTraffic());
    }
    public boolean getCityTraffic(){
        return student.getCurrentEvalutie().isCitytraffic();
    }
    
    public void switchDoubleLaneRoad(){
        student.getCurrentEvalutie().setDoublelane(!getDoubleLaneRoad());
    }
    public boolean getDoubleLaneRoad(){
        return student.getCurrentEvalutie().isDoublelane();
    }
    
    public void switchFueling(){
       student.getCurrentEvalutie().setFueling(!getCityTraffic());
    }
    public boolean getFueling(){
        return student.getCurrentEvalutie().isFueling();
    }
    
    public void switchGps(){
       student.getCurrentEvalutie().setGps(!getGps());
    }
    public boolean getGps(){
        return student.getCurrentEvalutie().isGps();
    }
    
    public void switchEmergencyStop(){
       student.getCurrentEvalutie().setEmergencystop(!getEmergencyStop());
    }
    public boolean getEmergencyStop(){
        return student.getCurrentEvalutie().isEmergencystop();
    }


    
}
