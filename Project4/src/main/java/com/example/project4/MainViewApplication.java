package com.example.project4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Applicaiton class for running the mainView application which starts the
 * program.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class MainViewApplication extends Application {
    @Override
    /**
     * Sets up the stage for the mainView application.
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainViewApplication.class.getResource("mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Welcome to RU Cafe!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method for running the program.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}