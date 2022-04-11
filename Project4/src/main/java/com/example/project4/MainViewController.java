package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for the main view which takes care of opening all the scenes.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class MainViewController {
    private Order order = new Order();
    private OrderBasketViewController basket;
    private Stage storeOrderStage = new Stage();
    private Scene storeOrderScene;


    /**
     * Getter for the order
     * @return the order
     */
    public Order getOrder() {
        return this.order;
    }


    /**
     * Resets the order object
     */
    public void resetOrder() {
        order = new Order();
    }

    /**
     * Opens the donut view
     * @param event
     */
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

    /**
     * Opens the coffee view
     * @param event
     */
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

    /**
     * Opens the order basket view
     * @param event
     */
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

    /**
     * Setter for store order stage after it is created from the basket view. Before it, the stage has null data
     * @param stage
     */
    public void setStoreOrderStage(Stage stage) {
        this.storeOrderStage = stage;
    }

    /**
     * Setter for store order stage after it is created from the basket view. Before it, the stage has null data
     * @param scene
     */
    public void setSceneOrderStage(Scene scene) {
        this.storeOrderScene = scene;
    }

    /**
     * Opens the store order view
     * @param event
     */
    @FXML
    public void openStoreOrderView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrderView.fxml"));
            storeOrderStage.setTitle("Store Orders");
            if(storeOrderScene == null) {
                storeOrderScene = new Scene(loader.load());
            }
            storeOrderStage.setScene(storeOrderScene);
            storeOrderStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}




