package com.example.project4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainViewController {
    private Order order = new Order();



    @FXML
    public void openDonutsView(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingDonutsView.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("OrderingDonutsViewController.fxml")));
            stage.setUserData(order);
            stage.setTitle("Donuts");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openCoffeeView(ActionEvent event) throws Exception {
        try {
            OrderingCoffeeViewController coffee = new OrderingCoffeeViewController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffeeView.fxml"));
            loader.setController(coffee);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Coffee");
            Scene coffeeScene = new Scene(root);
            stage.setScene(coffeeScene);
            coffee.initOrder(order);
            //stage.setUserData(order);

            //OrderingCoffeeViewController controller = loader.getController();
            //controller.goToCoffee(order);

            stage.show();

            //Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            /*
            Node node = (Node) event.getSource();
            Stage window = (Stage) node.getScene().getWindow();
            window.setTitle("Coffee");
            window.setScene(coffeeScene);
            window.show();
             */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openBasketView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderBasketView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setUserData(order);
            stage.setTitle("Basket Order");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openStoreOrderView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrderView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setUserData(order);
            stage.setTitle("Store Order");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}