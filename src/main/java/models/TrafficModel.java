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
public class TrafficModel extends Model {
    
    
    private final Student student;
    private final HashMap<String,Color> kleuren=new HashMap();

    public TrafficModel(Student student) {
        this.student = student;
        kleuren.put("red", Color.RED);
        kleuren.put("green", Color.GREEN);
        kleuren.put("orange", Color.ORANGE);
    }
    
     public void setColorForTechniek(String punt,String techniek){
        switch(techniek){
            case "sign": student.getCurrentTrafficTechnic().getSign().setColor(kleuren.get(punt));
                        break;
            case "speed": student.getCurrentTrafficTechnic().getSpeed().setColor(kleuren.get(punt));
                        break;
            case "distance": student.getCurrentTrafficTechnic().getDistance().setColor(kleuren.get(punt));
                        break;
            case "overtaking": student.getCurrentTrafficTechnic().getOvertaking().setColor(kleuren.get(punt));
                        break;
            case "crossing": student.getCurrentTrafficTechnic().getCrossing().setColor(kleuren.get(punt));
                        break;
            case "turningleft": student.getCurrentTrafficTechnic().getTurningleft().setColor(kleuren.get(punt));
                        break;
            case "turningright": student.getCurrentTrafficTechnic().getTurningright().setColor(kleuren.get(punt));
                        break;
            case "indicators": student.getCurrentTrafficTechnic().getIndicators().setColor(kleuren.get(punt));
                        break;
            case "publicroad": student.getCurrentTrafficTechnic().getPublicroad().setColor(kleuren.get(punt));
                        break;
        }
        fireInvalidationEvent();
                
    }
    
    public Color getPriorityColor(){
        return student.getCurrentTrafficTechnic().getPriority().getColor();
    }
    
    public Color getSignColor(){
        return student.getCurrentTrafficTechnic().getSign().getColor();
    }
    
    public Color getSpeedColor(){
        return student.getCurrentTrafficTechnic().getSpeed().getColor();
    }
    
    public Color getDistanceColor(){
        return student.getCurrentTrafficTechnic().getDistance().getColor();
    }
    
    public Color getOvertakingColor(){
        return student.getCurrentTrafficTechnic().getOvertaking().getColor();
    }
    
    public Color getCrossingColor(){
        return student.getCurrentTrafficTechnic().getCrossing().getColor();
    }
    
    public Color getTurningleftColor(){
        return student.getCurrentTrafficTechnic().getTurningleft().getColor();
    }
    
    public Color getTurningrightColor(){
        return student.getCurrentTrafficTechnic().getTurningright().getColor();
    }
    
    public Color getIndicatorsColor(){
        return student.getCurrentTrafficTechnic().getIndicators().getColor();
    }
    
    public Color getPublicroadColor(){
        return student.getCurrentTrafficTechnic().getPublicroad().getColor();
    }
    
     public void EvaNumberChanged(){
        fireInvalidationEvent();
    }
}
