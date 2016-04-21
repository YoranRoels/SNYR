/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domein.Color;
import domein.Student;
import java.util.HashMap;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author sande
 */
public class DriveModel extends StatusSuperModel {
   
   
    
    public DriveModel(Student student) {
        super(student);
    }
    
    
    public void setColorForTechniek(String punt){
        switch(id){
            case "clutch": student.getCurrentDriveTechnic().getClutch().setColor(kleuren.get(punt));
                        break;
            case "brake": student.getCurrentDriveTechnic().getBraking().setColor(kleuren.get(punt));
                        break;
            case "steering": student.getCurrentDriveTechnic().getSteering().setColor(kleuren.get(punt));
                        break;
            case "garage": student.getCurrentDriveTechnic().getGarage().setColor(kleuren.get(punt));
                        break;
            case "hill": student.getCurrentDriveTechnic().getHillBalancing().setColor(kleuren.get(punt));
                        break;
            case "looking": student.getCurrentDriveTechnic().getLooking().setColor(kleuren.get(punt));
                        break;
            case "parking": student.getCurrentDriveTechnic().getParking().setColor(kleuren.get(punt));
                        break;
            case "sitting": student.getCurrentDriveTechnic().getPosture().setColor(kleuren.get(punt));
                        break;
            case "reverse": student.getCurrentDriveTechnic().getReverse().setColor(kleuren.get(punt));
                        break;
            case "shifting": student.getCurrentDriveTechnic().getShifting().setColor(kleuren.get(punt));
                        break;
            case "steeringPractice": student.getCurrentDriveTechnic().getSteeringPractice().setColor(kleuren.get(punt));
                        break;
            case "turning": student.getCurrentDriveTechnic().getTurning().setColor(kleuren.get(punt));
                        break;
        }
        fireInvalidationEvent();
                
    }
    
    public void setCommentForTechniek(String comment)
    {
        switch(id){
            case "clutch": student.getCurrentDriveTechnic().getClutch().setComment(comment);
                        break;
            case "brake": student.getCurrentDriveTechnic().getBraking().setComment(comment);
                        break;
            case "steering": student.getCurrentDriveTechnic().getSteering().setComment(comment);
                        break;
            case "garage": student.getCurrentDriveTechnic().getGarage().setComment(comment);
                        break;
            case "hill": student.getCurrentDriveTechnic().getHillBalancing().setComment(comment);
                        break;
            case "looking": student.getCurrentDriveTechnic().getLooking().setComment(comment);
                        break;
            case "parking": student.getCurrentDriveTechnic().getParking().setComment(comment);
                        break;
            case "sitting": student.getCurrentDriveTechnic().getPosture().setComment(comment);
                        break;
            case "reverse": student.getCurrentDriveTechnic().getReverse().setComment(comment);
                        break;
            case "shifting": student.getCurrentDriveTechnic().getShifting().setComment(comment);
                        break;
            case "steeringPractice": student.getCurrentDriveTechnic().getSteeringPractice().setComment(comment);
                        break;
            case "turning": student.getCurrentDriveTechnic().getTurning().setComment(comment);
                        break;
        }
        fireInvalidationEvent();
                
    }
    
    public String getCommentForTechniek(){
        switch(id){
            case "clutch": return student.getCurrentDriveTechnic().getClutch().getComment();
                       
            case "brake": return student.getCurrentDriveTechnic().getBraking().getComment();
                      
            case "steering": return student.getCurrentDriveTechnic().getSteering().getComment();
                     
            case "garage": return student.getCurrentDriveTechnic().getGarage().getComment();
                    
            case "hill": return student.getCurrentDriveTechnic().getHillBalancing().getComment();
                      
            case "looking": return student.getCurrentDriveTechnic().getLooking().getComment();
                       
            case "parking": return student.getCurrentDriveTechnic().getParking().getComment();
                      
            case "sitting": return student.getCurrentDriveTechnic().getPosture().getComment();
                     
            case "reverse": return student.getCurrentDriveTechnic().getReverse().getComment();
                       
            case "shifting": return student.getCurrentDriveTechnic().getShifting().getComment();
                        
            case "steeringPractice": return student.getCurrentDriveTechnic().getSteeringPractice().getComment();
                        
            case "turning": return student.getCurrentDriveTechnic().getTurning().getComment();
            default: return "";
        }
                
    }
    
    public Color getClutchColor(){
        return student.getCurrentDriveTechnic().getClutch().getColor();
    }
    public Color getBrakingColor(){
        return student.getCurrentDriveTechnic().getBraking().getColor();
    }
    public Color getGarageColor(){
        return student.getCurrentDriveTechnic().getGarage().getColor();
    }
    public Color getHillColor(){
        return student.getCurrentDriveTechnic().getHillBalancing().getColor();
    }
    public Color getLookingColor(){
        return student.getCurrentDriveTechnic().getLooking().getColor();
    }
    public Color getParkingColor(){
        return student.getCurrentDriveTechnic().getParking().getColor();
    }
    public Color getPostureColor(){
        return student.getCurrentDriveTechnic().getPosture().getColor();
    }
    public Color getReverseColor(){
        return student.getCurrentDriveTechnic().getReverse().getColor();
    }
    public Color getShiftingColor(){
        return student.getCurrentDriveTechnic().getShifting().getColor();
    }
    public Color getSteeringColor(){
        return student.getCurrentDriveTechnic().getSteering().getColor();
    }
    public Color getSteeringPracticeColor(){
        return student.getCurrentDriveTechnic().getSteeringPractice().getColor();
    }
    public Color getTurningColor(){
        return student.getCurrentDriveTechnic().getTurning().getColor();
    }


    
    
}
