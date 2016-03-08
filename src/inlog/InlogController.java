/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inlog;

import controllers.HomeController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.RijschoolEva;

/**
 *
 * @author sande
 */
public class InlogController {
    
    @FXML
    private Button loginButton;
    
    private final Stage stage;

    public InlogController(Stage stage) {
        this.stage = stage;
    }
    
    
    
    public void initialize(){
        System.out.println("Inlog controller");
        loginButton.setOnAction((ActionEvent event) -> {
            try {
                System.out.println("open studenten fiche");
                FXMLLoader loader = new FXMLLoader(RijschoolEva.class.getResource("HomeScreen.fxml"));
                
                
                loader.setController(new HomeController(stage));
                Parent root = (Parent) loader.load();
                
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException ex) {
                Logger.getLogger(InlogController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
}
