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
public class TrafficModel extends StatusSuperModel {
    

    
    
    public TrafficModel(Student student) {
        super(student);
    }
    
    
    public void setColorForTechniek(String punt){
        switch(id){
            case "priority": student.getCurrentTrafficTechnic().getPriority().setColor(kleuren.get(punt));
                        break;
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
     
     public void setCommentForTechniek(String comment){
        switch(id){
            case "priority": student.getCurrentTrafficTechnic().getPriority().setComment(comment);
                        break;
            case "sign": student.getCurrentTrafficTechnic().getSign().setComment(comment);
                        break;
            case "speed": student.getCurrentTrafficTechnic().getSpeed().setComment(comment);
                        break;
            case "distance": student.getCurrentTrafficTechnic().getDistance().setComment(comment);
                        break;
            case "overtaking": student.getCurrentTrafficTechnic().getOvertaking().setComment(comment);
                        break;
            case "crossing": student.getCurrentTrafficTechnic().getCrossing().setComment(comment);
                        break;
            case "turningleft": student.getCurrentTrafficTechnic().getTurningleft().setComment(comment);
                        break;
            case "turningright": student.getCurrentTrafficTechnic().getTurningright().setComment(comment);
                        break;
            case "indicators": student.getCurrentTrafficTechnic().getIndicators().setComment(comment);
                        break;
            case "publicroad": student.getCurrentTrafficTechnic().getPublicroad().setComment(comment);
                        break;
        }
        fireInvalidationEvent();
                
    }
    
    public String getCommentForTechniek(){
        switch(id){
            case "priority": return student.getCurrentTrafficTechnic().getPriority().getComment();
                        
            case "sign": return student.getCurrentTrafficTechnic().getSign().getComment();
                        
            case "speed": return student.getCurrentTrafficTechnic().getSpeed().getComment();
                       
            case "distance": return student.getCurrentTrafficTechnic().getDistance().getComment();
                        
            case "overtaking": return student.getCurrentTrafficTechnic().getOvertaking().getComment();
                       
            case "crossing": return student.getCurrentTrafficTechnic().getCrossing().getComment();
                        
            case "turningleft": return student.getCurrentTrafficTechnic().getTurningleft().getComment();
                       
            case "turningright": return student.getCurrentTrafficTechnic().getTurningright().getComment();
                       
            case "indicators": return student.getCurrentTrafficTechnic().getIndicators().getComment();
                       
            case "publicroad": return student.getCurrentTrafficTechnic().getPublicroad().getComment();
                        
            default: return "";
        }
        
                
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
    
    
}
