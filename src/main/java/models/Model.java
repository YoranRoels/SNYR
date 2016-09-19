/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 *
 * @author sande
 */
public class Model implements Observable
{
    private List<InvalidationListener> listenerList = new ArrayList<>();

    protected void fireInvalidationEvent() {
        for (InvalidationListener listener : listenerList) {
            listener.invalidated(this);
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
         listenerList.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
       listenerList.remove(listener);
    }
    
    public void EvaNumberChanged(){
        fireInvalidationEvent();
    }
}
