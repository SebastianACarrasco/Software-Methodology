package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OrderingDonutsViewController {
    private double donutTotal = 0;
    Donuts donut = new Donuts();

    @FXML
    public ComboBox baseButton;
    @FXML
    public RadioButton strawberryFlavor;
    @FXML
    public RadioButton plainFlavor;
    @FXML
    public RadioButton berriesFlavor;
    @FXML
    public TextField donutsAmount;
    @FXML
    public TextField subtotalAmount;
    @FXML
    public Button addDonutsOrder;

    private MainViewController controller;
    /**
     * Initializes the combo box with the donut types
     */
    @FXML
    public void initialize() {
        baseButton.getItems().addAll("yeast", "cake", "holes");
        donut = new Donuts();
    }

    /**
     * setter for the main controller
     * @param controller
     */
    public void setMainController(MainViewController controller) {
        this.controller = controller;
    }

    /**
     * sends the donut order to the basket view and displays success if we have it
     * @param event
     */
    @FXML
    public void sendDonutToBasket(ActionEvent event) {
        try {
            if(!donut.getDonutType().equals("")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("orderBasketView.fxml"));
                Parent root = loader.load();
                if(this.donutTotal < 1) {
                    alertsMethodQuantity();
                    return;
                }
                this.donut.setTotal(donutTotal);
                this.controller.getOrder().add(this.donut);
                OrderBasketViewController basket = loader.getController();
                basket.setMainController(controller);

                Stage stage = new Stage();
                stage.setTitle("Order Basket");
                Scene basketScene = new Scene(root);
                stage.setScene(basketScene);
                donut = new Donuts();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Donut order has been placed");
                alert.setContentText("You can close this alert and continue to the basket.");
                alert.showAndWait();
                resetOrder();
                stage.show();
            } else {
                emptyDonut();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets donut type from gui and sets it to the donut object
     * @param event
     */
    @FXML
    public void getFlavor(ActionEvent event) {
        if(strawberryFlavor.isSelected()) {
            donut.setFlavor("Strawberry");
        } else if (plainFlavor.isSelected()){
            donut.setFlavor("Plain");
        } else if (berriesFlavor.isSelected()){
            donut.setFlavor("Berries");
        }
    }

    /**
     * Gets donut type from gui and sets it to the donut object
     * @param event
     */
    @FXML
    public void getDonutType(ActionEvent event) {
        donut.setDonutType(this.baseButton.getValue().toString());
        //this.donutTotal = donut.itemPrice();
    }

    /**
     * method to try and get the donut quantity and checks for validity of quantity.
     * @param event
     */
    @FXML
    public void getDonutQuantity(ActionEvent event) {
        int quantity = 0;
        try{
            quantity = Integer.parseInt(this.donutsAmount.getText());
            if(quantity < 1){
                alertsMethodQuantity();
                return;
            }
            donut.setQuantity(quantity);
            this.donutTotal = donut.itemPrice();
            printDonutSubTotal();
        } catch (NumberFormatException e) {
            alertsMethodQuantity();
        }
    }

    /**
     * resets the form for ordering donuts
     */
    @FXML
    private void resetOrder() {
        strawberryFlavor.setSelected(false);
        plainFlavor.setSelected(false);
        berriesFlavor.setSelected(false);
        baseButton.setValue("");
        donutsAmount.setText("");
        subtotalAmount.setText("");
    }

    /**
     * method for alerting a warning if a donut type is not selected
     */
    @FXML
    private void emptyDonut() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Order is not complete!");
        alert.setContentText("Please fill out all entities before placing order.");
        alert.showAndWait();
    }

    /**
     * method for alerting user that the order quantity is invalid
     */
    @FXML
    private void alertsMethodQuantity(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Order is not complete!");
        alert.setContentText("Please input a positive number for quantity and press enter");
        alert.showAndWait();
    }

    /**
     * Prints current subtotal to the gui
     */
    @FXML
    public void printDonutSubTotal() {
        subtotalAmount.setText(String.format("%.2f", this.donutTotal));
    }

}
