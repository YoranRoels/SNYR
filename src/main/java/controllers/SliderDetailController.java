package controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.HomeModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * FXML Controller class
 *
 * @author Yoran
 */
public class SliderDetailController implements InvalidationListener
{  
    @FXML
    private ImageView mainImage;
    
    private HomeModel model;
    
    public SliderDetailController(HomeModel model)
    {
        this.model = model;
        model.addListener(this);
    }
    
    public void initialize()
    {
        System.out.println("SliderDetailController");
        mainImage.setImage(new Image("/images/Slider_"+model.generatePictureNumber()+".png"));
    }

    @Override
    public void invalidated(Observable observable) {
        mainImage.setImage(new Image("/images/Slider_"+model.generatePictureNumber()+".png"));
    }
}
