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
public class StatusSuperModel extends Model{
    
    protected final Student student;
    protected final HashMap<String,Color> kleuren=new HashMap();
    
    /*de huidige Id en style voor de foto button*/
    protected String id ="";
    protected String style="";

    public StatusSuperModel(Student student) {
        this.student = student;
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
    
    @Override
    public void EvaNumberChanged(){
        id="";
        style="";
        fireInvalidationEvent();
    }
    
    
    
}
