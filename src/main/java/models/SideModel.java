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
 * 
 * model voor alle switches in de sidePanels
 */
public class SideModel extends Model {
    
    private final Student student;
    private final HashMap<String,Color> kleuren=new HashMap();
    
    /*de huidige Id en style voor de foto button*/
    private String id ="";
    private String style="";
    
    public SideModel(Student student){
        this.student=student;
        kleuren.put("red", Color.RED);
        kleuren.put("green", Color.GREEN);
        kleuren.put("orange", Color.ORANGE);
    }
    
    public void setIdEnStyle(String id,String style){
        this.id=id;
        this.style=style;
        fireInvalidationEvent();
    }
    
    /*de id voor de fotoButton*/
    public String getId(){
        return id;
    }
    
    public String getStyle(){
        return style;
    }
    
    public void setColorForSkills(String punt)
    {
        switch(id){
            case "tires": student.getCurrentSkills().getTires().setColor(kleuren.get(punt));
                        break;
            case "city": student.getCurrentSkills().getCity().setColor(kleuren.get(punt));
                        break;
            case "doublelane": student.getCurrentSkills().getDoublelane().setColor(kleuren.get(punt));
                        break;
            case "gps": student.getCurrentSkills().getGps().setColor(kleuren.get(punt));
                        break;
            case "lights": student.getCurrentSkills().getLights().setColor(kleuren.get(punt));
                        break;
            case "emergencystop": student.getCurrentSkills().getEmergencystop().setColor(kleuren.get(punt));
                        break;
            case "oilcheck": student.getCurrentSkills().getOilcheck().setColor(kleuren.get(punt));
                        break;
            case "roundabout": student.getCurrentSkills().getRoundabout().setColor(kleuren.get(punt));
                        break;
            case "highway": student.getCurrentSkills().getHighway().setColor(kleuren.get(punt));
                        break;
            case "fueling": student.getCurrentSkills().getFueling().setColor(kleuren.get(punt));
                        break;
        }
        fireInvalidationEvent();
    }  
    
    public void setCommentForSkills(String comment)
    {
        switch(id){
            case "tires": student.getCurrentSkills().getTires().setComment(comment);
                        break;
            case "city": student.getCurrentSkills().getCity().setComment(comment);
                        break;
            case "doublelane": student.getCurrentSkills().getDoublelane().setComment(comment);
                        break;
            case "gps": student.getCurrentSkills().getGps().setComment(comment);
                        break;
            case "lights": student.getCurrentSkills().getLights().setComment(comment);
                        break;
            case "emergencystop": student.getCurrentSkills().getEmergencystop().setComment(comment);
                        break;
            case "oilcheck": student.getCurrentSkills().getOilcheck().setComment(comment);
                        break;
            case "roundabout": student.getCurrentSkills().getRoundabout().setComment(comment);
                        break;
            case "highway": student.getCurrentSkills().getHighway().setComment(comment);
                        break;
            case "fueling": student.getCurrentSkills().getFueling().setComment(comment);
                        break;
        }
        fireInvalidationEvent();
    }  
    
    public String getCommentForSkills()
    {
        switch(id){
            case "tires": return student.getCurrentSkills().getTires().getComment();
                        
            case "city": return student.getCurrentSkills().getCity().getComment();
                        
            case "doublelane": return student.getCurrentSkills().getDoublelane().getComment();
                        
            case "gps": return student.getCurrentSkills().getGps().getComment();
                        
            case "lights": return student.getCurrentSkills().getLights().getComment();
                        
            case "emergencystop": return student.getCurrentSkills().getEmergencystop().getComment();
                        
            case "oilcheck": return student.getCurrentSkills().getOilcheck().getComment();
                        
            case "roundabout": return student.getCurrentSkills().getRoundabout().getComment();
                        
            case "highway": return student.getCurrentSkills().getHighway().getComment();
                        
            case "fueling": return student.getCurrentSkills().getFueling().getComment();
                        
            default: return"";
        }
    }
    
    public Color getTiresColor(){
        return student.getCurrentSkills().getTires().getColor();
    }
    
    public Color getCityColor(){
        return student.getCurrentSkills().getCity().getColor();
    }
    
    public Color getDoublelaneColor(){
        return student.getCurrentSkills().getDoublelane().getColor();
    }
    
    public Color getGpsColor(){
        return student.getCurrentSkills().getGps().getColor();
    }
    
    public Color getLightsColor(){
        return student.getCurrentSkills().getLights().getColor();
    }
    
    public Color getEmergencystopColor(){
        return student.getCurrentSkills().getEmergencystop().getColor();
    }
    
    public Color getOilcheckColor(){
        return student.getCurrentSkills().getOilcheck().getColor();
    }
    
    public Color getRoundaboutColor(){
        return student.getCurrentSkills().getRoundabout().getColor();
    }
    
    public Color getHighwayColor(){
        return student.getCurrentSkills().getHighway().getColor();
    }
    
    public Color getFuelingColor(){
        return student.getCurrentSkills().getFueling().getColor();
    }
}
