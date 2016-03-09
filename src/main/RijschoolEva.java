/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.InlogController;
import java.io.IOException;
import javafx.application.Application;
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
        FXMLLoader loader = new FXMLLoader(AnchorWheel.class.getResource("InlogScreen.fxml"));

        loader.setController(new InlogController(stage));
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
