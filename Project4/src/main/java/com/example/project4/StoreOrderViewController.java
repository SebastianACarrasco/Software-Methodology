package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;

public class StoreOrderViewController {
    private OrderBasketViewController controller;
    private static StoreOrders storeOrder = new StoreOrders();

    @FXML
    private ComboBox customerStoreOrderNumber;
    @FXML
    private TextArea storeOrderTotal;
    @FXML
    private ListView storeOrderListView;
    @FXML
    private Button cancelOrder;

    public void setMainControllerForStoreOrder(OrderBasketViewController controller) {
        this.controller = controller;
        putOrders();
    }

    private void putOrders() {
        customerStoreOrderNumber.getItems().add(this.controller.getOrder().getID());
        storeOrder.add(this.controller.getOrder());
        printOrders();
    }

    private void printOrders() {
        for(Order o : storeOrder.getStoreOrders().values()) {
            storeOrderListView.getItems().add(o.toString());
        }
        //storeOrderListView.getItems().add(storeOrder.toString());
    }

}
