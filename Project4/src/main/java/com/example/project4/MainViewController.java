package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.util.Objects;

public class MainViewController {
    private Order order = new Order();
    private StoreOrders store = new StoreOrders();
    private OrderBasketViewController basket;


    /**
     * Getter for the order
     * @return the order
     */
    public Order getOrder() {
        return this.order;
    }

    public void resetOrder() {
        order = new Order();
    }

    @FXML
    public void openDonutsView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingDonutsView.fxml"));
            Parent root = loader.load();

            OrderingDonutsViewController donut = loader.getController();
            donut.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Donut");
            Scene donutScene = new Scene(root);
            stage.setScene(donutScene);
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

            OrderingCoffeeViewController coffee = loader.getController();
            coffee.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Coffee");
            Scene coffeeScene = new Scene(root);
            stage.setScene(coffeeScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void openBasketView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderBasketView.fxml"));
            Parent root = loader.load();

            basket = loader.getController();
            basket.setMainController(this);

            Stage stage = new Stage();
            stage.setTitle("Order Basket");
            Scene basketScene = new Scene(root);
            stage.setScene(basketScene);
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

            StoreOrderViewController storeOrder = loader.getController();
            storeOrder.setMain(basket);

            Stage stage = new Stage();
            stage.setTitle("Store Orders");
            Scene storeScene = new Scene(root);
            stage.setScene(storeScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}