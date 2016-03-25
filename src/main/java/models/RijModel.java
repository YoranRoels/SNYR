/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.models;

import main.java.domein.Color;
import main.java.domein.Student;
import java.util.HashMap;
import javafx.scene.control.Button;

/**
 *
 * @author sande
 */
public class RijModel extends Model {
   
    private final Student student;
    private HashMap<String,Color> kleuren=new HashMap();

    public RijModel(Student student) {
        this.student = student;
        kleuren.put("red", Color.RED);
        kleuren.put("green", Color.GREEN);
        kleuren.put("orange", Color.ORANGE);
    }
    
    public void setColorForTechniek(String punt,String techniek){
        switch(techniek){
            case "clutch": student.getCurrentRijtechniek().getClutch().setColor(kleuren.get(punt));
                        break;
            case "brake": student.getCurrentRijtechniek().getBraking().setColor(kleuren.get(punt));
                        break;
            case "steering": student.getCurrentRijtechniek().getSteering().setColor(kleuren.get(punt));
                        break;
            case "garage": student.getCurrentRijtechniek().getGarage().setColor(kleuren.get(punt));
                        break;
            case "hill": student.getCurrentRijtechniek().getHillBalancing().setColor(kleuren.get(punt));
                        break;
            case "looking": student.getCurrentRijtechniek().getLooking().setColor(kleuren.get(punt));
                        break;
            case "parking": student.getCurrentRijtechniek().getParking().setColor(kleuren.get(punt));
                        break;
            case "sitting": student.getCurrentRijtechniek().getPosture().setColor(kleuren.get(punt));
                        break;
            case "reverse": student.getCurrentRijtechniek().getReverse().setColor(kleuren.get(punt));
                        break;
            case "shifting": student.getCurrentRijtechniek().getShifting().setColor(kleuren.get(punt));
                        break;
            case "steeringPractice": student.getCurrentRijtechniek().getSteeringPractice().setColor(kleuren.get(punt));
                        break;
            case "turning": student.getCurrentRijtechniek().getTurning().setColor(kleuren.get(punt));
                        break;
        }
        fireInvalidationEvent();
                
    }
    
    public Color getClutchColor(){
        return student.getCurrentRijtechniek().getClutch().getColor();
    }
    public Color getBrakingColor(){
        return student.getCurrentRijtechniek().getBraking().getColor();
    }
    public Color getGarageColor(){
        return student.getCurrentRijtechniek().getGarage().getColor();
    }
    public Color getHillColor(){
        return student.getCurrentRijtechniek().getHillBalancing().getColor();
    }
    public Color getLookingColor(){
        return student.getCurrentRijtechniek().getLooking().getColor();
    }
    public Color getParkingColor(){
        return student.getCurrentRijtechniek().getParking().getColor();
    }
    public Color getPostureColor(){
        return student.getCurrentRijtechniek().getPosture().getColor();
    }
    public Color getReverseColor(){
        return student.getCurrentRijtechniek().getReverse().getColor();
    }
    public Color getShiftingColor(){
        return student.getCurrentRijtechniek().getShifting().getColor();
    }
    public Color getSteeringColor(){
        return student.getCurrentRijtechniek().getSteering().getColor();
    }
    public Color getSteeringPracticeColor(){
        return student.getCurrentRijtechniek().getSteeringPractice().getColor();
    }
    public Color getTurningColor(){
        return student.getCurrentRijtechniek().getTurning().getColor();
    }
    
    public void EvaNumberChanged(){
        fireInvalidationEvent();
    }
    
    
    
}
