/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import controllers.InlogController;
import domein.Student;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panels.AnchorWheel;

/**
 *
 * @author Yoran
 */
public class RijschoolEva extends Application {

    @Override
    public void start(Stage stage) throws IOException {
    
            
        ObservableList<String> selectie = FXCollections.observableArrayList("Zenuwachtig","Concentractie",
            "Schrik","Asociaal","Verkeersgevaarlijk","Ongeduldig","Agressief rijgedrag","Inzet","Verstrooid","Eigenwijs");

        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));

        loader.setController(new InlogController(stage,selectie));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root,1024,800);

        stage.setScene(scene);

        stage.setTitle("Eva rijschool");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
