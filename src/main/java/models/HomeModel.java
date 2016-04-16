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
public class HomeModel extends Model {
    
    private final Student student;

    public HomeModel(Student student) {
        this.student = student;
    }
    
    public void setProgres(double i){
        student.setCurrentProgres(i);
    }
    public double getProgres(){
        return student.getCurrentProgres();
    }
    
}
