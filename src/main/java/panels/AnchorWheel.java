/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.panels;

import main.java.commands.SwitchPanelCommand;
import main.java.controllers.WheelController;
import main.java.domein.Student;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.java.models.RijModel;

/**
 *
 * @author sande
 */
public class AnchorWheel extends AnchorPane {
    
    private AnchorSide anchorSide;
    private AnchorDrive anchorDrive;
    
    private final BorderPane root;

    public AnchorSide getAnchorSide() {
        return anchorSide;
    }

    public void setAnchorSide(AnchorSide anchorSide) {
        this.anchorSide = anchorSide;
    }
    
    public void setAnchorDrive(AnchorDrive anchorDrive) {
        this.anchorDrive = anchorDrive;
    }
    
    public AnchorDrive getAnchorDrive() {
        return anchorDrive;
    }

    private final RijModel rijModel;
    public AnchorWheel(BorderPane root,RijModel model) {
        this.root = root;
        this.rijModel=model;
    }
    
    public void create(){
        
     try{
            FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("WheelPane.fxml"));
            loader.setRoot(this);
            loader.setController(new WheelController(root,rijModel));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }

     this.setOnMouseClicked(new SwitchPanelCommand(root, anchorSide));
    }   

    
}
