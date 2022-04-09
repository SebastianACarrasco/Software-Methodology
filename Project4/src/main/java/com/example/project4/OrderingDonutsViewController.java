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

    public void setMainController(MainViewController controller) {
        this.controller = controller;
    }
    /**
     * Receives data from previous view and sends it to the next view
     * @param event
     */

    @FXML
    public void receiveUserData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();
        order.add(donut);
    }

    @FXML
    public void sendToBasket(ActionEvent event) {
        try {
            if(!donut.getDonutType().equals("")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("orderBasketView.fxml"));
                Parent root = loader.load();

                this.donut.setTotal(donutTotal);
                this.controller.getOrder().add(this.donut);
                OrderBasketViewController basket = loader.getController();
                basket.setMainController(controller);

                Stage stage = new Stage();
                stage.setTitle("Order Basket");
                Scene basketScene = new Scene(root);
                stage.setScene(basketScene);
                donut = new Donuts();
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
        this.donutTotal = donut.itemPrice();
        printSubTotal();
    }

    @FXML
    public void getDonutQuantity(ActionEvent event) {
        donut.setQuantity(Integer.parseInt(this.donutsAmount.getText()));
        printSubTotal();
    }

    @FXML
    private void resetOrder() {
        strawberryFlavor.setSelected(false);
        plainFlavor.setSelected(false);
        berriesFlavor.setSelected(false);
        baseButton.setValue("");
    }

    @FXML
    private void emptyDonut() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Order is not complete!");
        alert.setContentText("Please fill out all entities before placing order.");
        alert.showAndWait();
    }

    /**
     * Prints current subtotal to the gui
     */
    @FXML
    public void printSubTotal() {
        subtotalAmount.setText(String.format("%.2f", this.donutTotal));
    }

}
