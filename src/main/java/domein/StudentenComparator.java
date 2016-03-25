/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.domein;

import java.util.Comparator;

/**
 *
 * @author sande
 */
public class StudentenComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getAchternaam().equals(o2.getAchternaam())){
            return 0;
        }else{
            return o1.getAchternaam().compareTo(o2.getAchternaam());
        }
            }
    
}
