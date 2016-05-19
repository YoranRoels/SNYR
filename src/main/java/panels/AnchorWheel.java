/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import controllers.WheelController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.AttitudeModel;
import models.DriveModel;
import models.SkillsModel;
import models.TrafficModel;

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

    private final DriveModel driveModel;
    private final TrafficModel trafficModel;
    private final AttitudeModel attitudeModel;
    private final SkillsModel skillsModel;
    
    public AnchorWheel(BorderPane root,DriveModel driveModel,TrafficModel trafficModel,AttitudeModel attitudeModel,SkillsModel skillsModel) {
        this.root = root;
        this.driveModel=driveModel;
        this.trafficModel=trafficModel;
        this.attitudeModel=attitudeModel;
        this.skillsModel=skillsModel;
    }
    
    public void create(){
        
     try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/WheelPane.fxml"));
            loader.setRoot(this);
            loader.setController(new WheelController(root,driveModel,trafficModel,attitudeModel,skillsModel));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }   

    
}
