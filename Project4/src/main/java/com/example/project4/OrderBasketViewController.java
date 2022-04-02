package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.stage.Stage;

public class OrderBasketViewController {
    private static int ID;
    @FXML
    public ListView display;

    public OrderBasketViewController() {
        this.ID++;
    }


    @FXML
    public void displayOrderBasket() {

    }

    @FXML
    public void receiveOrderBasket(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();

        StoreOrders storeOrders = new StoreOrders();
        storeOrders.ordersPlaced(order);
    }
}
