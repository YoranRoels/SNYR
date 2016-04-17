/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domein.Student;
import javafx.collections.ObservableList;

/**
 *
 * @author sande
 */
public class AttitudeModel extends Model {
    
    private final Student student;
    
    /*lijst met de selectie woorden*/
    private final ObservableList<String> selectie;

    public AttitudeModel(Student student,ObservableList<String> selectie) {
        this.student = student;
        this.selectie=selectie;
    }
    
    public void setAttitude(String attitude){
        student.setCurrentAttitude(attitude);
    }
    
    public String getAttitude(){
        return student.getCurrentAttitude();
    }
    
    public void addNieuwSelectieWoord(String woord){
        selectie.add(woord);
        fireInvalidationEvent();
    }
    
    public ObservableList<String> getSelectie(){
        return selectie;
    }
    
   
    
}
