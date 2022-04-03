package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

public class mainViewController {
    @FXML
    public Order order = new Order();


    @FXML
    public void openDonutsView(ActionEvent event) {
        Node node = (Node) event.getSource();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingDonutsView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setUserData(order);
            stage.setTitle("Donuts");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openCoffeeView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingCoffeeView.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setUserData(order);
            stage.setTitle("Coffee");
            stage.setScene(new Scene(root));
            stage.show();
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