/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package start;

import main.java.controllers.InlogController;
import main.java.domein.Student;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.panels.AnchorWheel;

/**
 *
 * @author Yoran
 */
public class RijschoolEva extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ObservableList<Student> studenten = 
            FXCollections.observableArrayList(new Student("Sander","Nijs","sander@mail.com","url"),new Student("Yoran","Roels","yoran@mail.com","url"),new Student("Jos","Abels","yoran@mail.com","url"));
    
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/panels/InlogScreen.fxml"));

        loader.setController(new InlogController(stage,studenten));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root);

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