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
        putOrders();
    }

    //empty listview but need to use combobox to show orders with unique ID
    private void putOrders() {
        storeOrder.add(this.controller.getOrder());
        customerStoreOrderNumber.getItems().add(this.controller.getOrder().getID());
        storeOrderTotal.setText(String.format("%.2f", this.controller.getOrder().subTotalWithTax()));

        for (Map.Entry<Integer, Order> mapElement : storeOrder.getStoreOrders().entrySet()) {
            customerStoreOrderNumber.getItems().add(mapElement.getKey());
        }

        //printOrders();
    }

    @FXML
    public void getOrderInfo(ActionEvent event) {
        storeOrderListView.getItems().add(storeOrder.getStoreOrders().get(this.controller.getOrder().getID()));
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

    }

    void noOrderSelectedWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("No order selected for removal");
        alert.setContentText("Please choose an order to view.");
        alert.showAndWait();
    }

}
