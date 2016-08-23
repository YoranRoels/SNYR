/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Comparator;

/**
 *
 * @author sande
 */
public class StudentenComparator implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) 
    {
        int res = o1.getAchternaam().compareToIgnoreCase(o2.getAchternaam());
        if (res != 0) 
        {
            return res;
        }
        return o1.getVoornaam().compareToIgnoreCase(o2.getVoornaam());
    }
}
