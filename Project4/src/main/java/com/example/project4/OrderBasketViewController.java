package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import java.util.ArrayList;

public class OrderBasketViewController {
    private MainViewController controller;

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
    @FXML
    public Button basketRemove;

    public void setMainController(MainViewController controller) {
        this.controller = controller;
        initBasket(this.controller.getOrder());
    }

    private void initBasket(Order order) {
        ArrayList<MenuItem> items = order.getItems();
        for(int i = 0; i < items.size(); i++) {
            basketListView.getItems().add(items.get(i).toString());
        }

        basketViewOrderNumber.setText(Integer.toString(order.getID()));
        basketSalesTax.setText(String.format("%.2f", order.getTaxes()));
        basketSubtotal.setText(String.format("%.2f", order.subTotal()));
        basketOrderTotal.setText(String.format("%.2f", order.subTotalWithTax()));
    }

    private void recalculateBasket() {
        basketSubtotal.setText(String.format("%.2f",controller.getOrder().subTotal()));
        basketSalesTax.setText(String.format("%.2f",controller.getOrder().getTaxes()));
        basketOrderTotal.setText(String.format("%.2f",controller.getOrder().subTotalWithTax()));
    }

    @FXML
    void removeOrder(ActionEvent event) {
        String order = basketListView.getSelectionModel().getSelectedItem();
        int orderIndex = basketListView.getSelectionModel().getSelectedIndex();

        if(order == null) {
            noOrderFound();
            return;
        }
        basketListView.getItems().remove(orderIndex);
        this.controller.getOrder().removeItem(this.controller.getOrder().getItems());
        recalculateBasket();
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
                return;
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

    void noOrderFound() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("No item selected for removal");
        alert.setContentText("Please choose an item to remove");
        alert.showAndWait();
    }
}
