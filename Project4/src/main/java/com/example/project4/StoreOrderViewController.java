package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        customerStoreOrderNumber.getItems().removeAll(customerStoreOrderNumber.getItems());
        sizeNumberOfOrders();
    }

    @FXML
    public void getOrderInfo(ActionEvent event) {
        storeOrderListView.getItems().clear();
        int key = (int) customerStoreOrderNumber.getSelectionModel().getSelectedItem();
        storeOrderTotal.setText(String.format("%.2f", storeOrder.getStoreOrders().get(key).subTotalWithTax()));
        storeOrderListView.getItems().add(storeOrder.getStoreOrders().get(key).toString());
    }

    @FXML
    public void cancelOrder(ActionEvent event) {
        if(customerStoreOrderNumber.getSelectionModel().getSelectedItem() == null) {
            noOrderSelectedWarning();
        } else {
            storeOrderListView.getItems().clear();
            storeOrderTotal.setText("");
            //customerStoreOrderNumber.getItems().remove(this.controller.getOrder().getID());
            customerStoreOrderNumber.getItems().clear();
            storeOrder.remove(this.controller.getOrder().getID());
            sizeNumberOfOrders();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Order has been canceled");
            alert.setContentText("You can close this alert and export.");
            alert.showAndWait();
        }
    }

    private void sizeNumberOfOrders() {
        for (Map.Entry<Integer, Order> mapElement : storeOrder.getStoreOrders().entrySet()) {
            customerStoreOrderNumber.getItems().add(mapElement.getKey());
        }
    }

    @FXML
    public void exportOrder(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open target file for exporting");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", ".txt"),
                new FileChooser.ExtensionFilter("All Files", ".*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);
        try {
            FileWriter myWriter = new FileWriter(targetFile);
            for (Map.Entry<Integer, Order> mapElement : storeOrder.getStoreOrders().entrySet()) {
                myWriter.write(mapElement.getKey() + " " + mapElement.getValue().toString());
                myWriter.write("\n");
            }
            myWriter.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("File Created!");
            alert.setContentText("The StoreOrders.txt file has been created with store data");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Cannot Add to File.");
            alert.setContentText("For some reason, the txt file can't be created or edited.");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void noOrderSelectedWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("No order selected");
        alert.setContentText("Please choose an order.");
        alert.showAndWait();
    }

}
