/*
 * Course:     SE 2811
 * Term:       Winter 2020-21
 * Assignment: Final Presentation
 * Author:     Mitchell Mahnke, Alex Moran
 * Date:       2/10/22
 */

package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
        primaryStage.setTitle("Group 04 Thread Pool Pattern");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
