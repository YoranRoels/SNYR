/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import domein.Color;
import domein.Student;
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
    
    public void selecteerTechniek(String punt,String techniek){
        switch(techniek){
            case "clutch": System.out.println("clutch");
                           System.out.println(kleuren.get(punt));
                           student.getCurrentRijtechniek().getClutch().setColor(kleuren.get(punt));
                        break;
            case "braking": System.out.println("braking");
                        break;
            case "steering": System.out.println("steering");
                        break;
        }
                
    }
    
    public Color getClutchColor(){
        return student.getCurrentRijtechniek().getClutch().getColor();
    }
    
    
    
}
