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
    private final BorderPane root;
    
    private SkillsModel model;

    public AnchorSide(BorderPane root,SkillsModel model) {
        this.root = root; 
        this.model=model;
    }
    
    public void create(){
        
     try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/Sidepane.fxml"));
            loader.setRoot(this);
            loader.setController(new SideController(root,model));
            loader.load();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }
    
}
