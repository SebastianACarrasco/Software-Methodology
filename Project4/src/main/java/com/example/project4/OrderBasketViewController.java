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


/**
 * Controller for the order basket. Displays the current order
 * and lets the user remove items from the order and place.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class OrderBasketViewController {
    private MainViewController controller;

    @FXML
    private ListView<String> basketListView;
    @FXML
    private TextArea basketSubtotal;
    @FXML
    private TextArea basketSalesTax;
    @FXML
    private TextArea basketOrderTotal;
    @FXML
    private TextArea basketViewOrderNumber;
    @FXML
    private Button basketRemove;


    /**
     * Setter for the main controller of this view.
     * @param controller
     */
    public void setMainController(MainViewController controller) {
        this.controller = controller;
        initBasket(this.controller.getOrder());
    }

    /**
     * Initializes the order basket with the order items.
     * @param order
     */
    private void initBasket(Order order) {
        basketViewOrderNumber.setText(Integer.toString(order.getID()));

        ArrayList<MenuItem> items = order.getItems();
        for(int i = 0; i < items.size(); i++) {
            basketListView.getItems().add(items.get(i).toString());
        }

        basketSalesTax.setText(String.format("%.2f", order.getTaxes()));
        basketSubtotal.setText(String.format("%.2f", order.subTotal()));
        basketOrderTotal.setText(String.format("%.2f", order.subTotalWithTax()));
    }

    /**
     * Removes selected order from the basket.
     * @param event
     */
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

        basketSubtotal.setText(String.format("%.2f",this.controller.getOrder().subTotal()));
        basketSalesTax.setText(String.format("%.2f",this.controller.getOrder().getTaxes()));
        basketOrderTotal.setText(String.format("%.2f",this.controller.getOrder().subTotalWithTax()));

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Success!");
        alert.setHeaderText("Order has been removed from the basket.");
        alert.setContentText("You can close this alert and keep adding items or checkout.");
        alert.showAndWait();
    }

    /**
     * Places the order by sending the order over to the store Order database.
     * @param actionEvent
     */
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
                controller.setStoreOrderStage(stage);
                controller.setSceneOrderStage(storeOrderScene);
                stage.hide();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Order has been placed");
                alert.setContentText("You can close this alert and continue.");
                alert.showAndWait();
                clearBasket();
            } else {
                emptyBasketAlert();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the order basket.
     */
    private void clearBasket() {
        controller.resetOrder();
        basketListView.getItems().clear();
        basketViewOrderNumber.setText("");
        basketSubtotal.setText("");
        basketSalesTax.setText("");
        basketOrderTotal.setText("");
    }

    /**
     * Alerts the user that the order basket is empty so action cannt be done.
     */
    private void emptyBasketAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Basket is empty!");
        alert.setContentText("Please put something before making an order.");
        alert.showAndWait();
    }

    /**
     * Getter for the current controller order.
     * @return Order object
     */
    public Order getOrder() {
       return controller.getOrder();
    }

    /**
     * Alert for no order found and action cannt be done.
     */
    void noOrderFound() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("No item selected for removal");
        alert.setContentText("Please choose an item to remove");
        alert.showAndWait();
    }
}
