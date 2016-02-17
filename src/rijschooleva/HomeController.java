/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rijschooleva;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;



/**
 *
 * @author sande
 */
public class HomeController {
    
    @FXML
    private ImageView profielFoto;
    @FXML
    private CheckBox checkBox1;
    
    
    public void initialize(){
    //main setup initialize van de gui
        System.out.println("Start initliaze");
        
        //set profil pic van de user
        //profielFoto.setImage(null);
        
        //set on action   ... 
        checkBox1.setOnAction((ActionEvent event) -> {
            System.out.println("register evalutation 1");
                    });
    }
    
    
    public void openProfiel(){
        System.out.println("Open profiel");
    }
    
}
