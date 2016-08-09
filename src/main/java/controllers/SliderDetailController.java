package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
public class SliderDetailController 
{  
    @FXML
    public ImageView mainImage;
    
    public int sliderProgress;
    
    public SliderDetailController(int sliderProgress)
    {
        this.sliderProgress = sliderProgress;
    }
    
    public void initialize()
    {
        mainImage.setImage(new Image("/images/Slider_"+sliderProgress+".png"));
    }
}
