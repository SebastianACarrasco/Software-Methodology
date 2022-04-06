package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class OrderBasketViewController {
    private mainViewController controller;

    @FXML
    public ListView<String> basketListView;
    @FXML
    public TextArea basketSubtotal;
    @FXML
    public TextArea basketSalesTax;
    @FXML
    public TextArea basketOrderTotal;
    @FXML
    public TextArea basketViewOrderNumber;

    public void setMainController(mainViewController controller) {
        this.controller = controller;
        initBasket(this.controller.getOrder());
    }

    public void initBasket(Order order) {
        basketListView.getItems().add(order.toString());;
        basketViewOrderNumber.setText(Integer.toString(order.getID()));
        basketSubtotal.setText(order.subTotal());
        basketSalesTax.setText(order.getTaxes());
        basketOrderTotal.setText(order.subTotalWithTax());
    }

    @FXML
    public void placeOrder(ActionEvent actionEvent) {
        try {
            if(!basketListView.getItems().isEmpty()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("storeOrderView.fxml"));
                Parent root = loader.load();

                StoreOrderViewController storeOrder = loader.getController();
                storeOrder.setMainControllerForStoreOrder(this);

                Stage stage = new Stage();
                stage.setTitle("Store Orders");
                Scene storeOrderScene = new Scene(root);
                stage.setScene(storeOrderScene);
                clearBasket();
                stage.show();
            } else {
                emptyBasketAlert();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearBasket() {
        controller.resetOrder();
        basketListView.getItems().clear();
        basketViewOrderNumber.setText("");
        basketSubtotal.setText("");
        basketSalesTax.setText("");
        basketOrderTotal.setText("");
    }

    private void emptyBasketAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Basket is empty!");
        alert.setContentText("Please put something before making an order.");
        alert.showAndWait();
    }

    public Order getOrder() {
       return controller.getOrder();
    }
}
