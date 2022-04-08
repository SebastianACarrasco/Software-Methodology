package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import java.util.Map;

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
        storeOrder.add(this.controller.getOrder());//adds order to storeOrder map
        //putOrders();
        //customerStoreOrderNumber.getItems().add(this.controller.getOrder().getID());
    }


    private void putOrders() {
        //storeOrder.add(this.controller.getOrder());//adds order to storeOrder map
        storeOrderTotal.setText(String.format("%.2f", this.controller.getOrder().subTotalWithTax()));

        for (Map.Entry<Integer, Order> mapElement : storeOrder.getStoreOrders().entrySet()) {
            customerStoreOrderNumber.getItems().add(mapElement.getKey());
            storeOrderListView.getItems().add(mapElement.getValue().toString());
        }
    }


    @FXML
    public void getOrderInfo(ActionEvent event) {

    }

    @FXML
    public void cancelOrder(ActionEvent event) {
        if (storeOrderListView.getSelectionModel().getSelectedItem() == null) {
            noOrderSelectedWarning();
        } else {
            storeOrder.remove(this.controller.getOrder());
            storeOrderListView.getItems().clear();
            customerStoreOrderNumber.getItems().remove(this.controller.getOrder().getID());
        }
    }

    @FXML
    public void exportOrder(ActionEvent event) {
        //export the hashmap to a file
    }

    void noOrderSelectedWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("No order selected");
        alert.setContentText("Please choose an order.");
        alert.showAndWait();
    }

}
