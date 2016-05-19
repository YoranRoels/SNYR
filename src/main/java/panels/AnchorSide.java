/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import controllers.SideController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import models.SkillsModel;

/**
 *
 * @author sande
 */
public class AnchorSide extends AnchorPane
{
    private AnchorWheel anchorWheel;
    
    private final BorderPane root;
    
    private SkillsModel switchModel;

    public AnchorSide(BorderPane root,SkillsModel switchModel) {
        this.root = root; 
        this.switchModel=switchModel;
    }

    public AnchorWheel getAnchorWheel() {
        return anchorWheel;
    }

    public void setAnchorWheel(AnchorWheel anchorWheel) {
        this.anchorWheel = anchorWheel;
    }
    
    public void create(){
        
     try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/Sidepane.fxml"));
            loader.setRoot(this);
            loader.setController(new SideController(root,switchModel));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
    
}
