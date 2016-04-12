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
 */
public class AttitudeModel extends Model {
    
    private final Student student;

    public AttitudeModel(Student student) {
        this.student = student;
    }
    
    public void setAttitude(String attitude){
        student.setCurrentAttitude(attitude);
    }
    
    public String getAttitude(){
        return student.getCurrentAttitude();
    }
    
   
    
}
