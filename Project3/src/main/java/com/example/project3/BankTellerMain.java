package com.example.project3;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This is the main class for running the GUI application as it contains the
 * main method. This sets up the stage, so the dimensions and title are created
 * here.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class BankTellerMain extends Application {

    /**
     * The start method sets up the stage and scene with dimensions and title
     * and lastly displays it.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BankTellerMain.class.getResource("BankTellerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        stage.setTitle("BankTeller");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method for running the application as it only contains launch().
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}