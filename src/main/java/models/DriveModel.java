/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domein.Color;
import domein.Student;
import java.util.HashMap;

/**
 *
 * @author sande
 */
public class DriveModel extends Model {
   
    private final Student student;
    private final HashMap<String,Color> kleuren=new HashMap();

    public DriveModel(Student student) {
        this.student = student;
        kleuren.put("red", Color.RED);
        kleuren.put("green", Color.GREEN);
        kleuren.put("orange", Color.ORANGE);
    }
    
    public void setColorForTechniek(String punt,String techniek){
        switch(techniek){
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
    
    public void EvaNumberChanged(){
        fireInvalidationEvent();
    }
    
    
    
}
