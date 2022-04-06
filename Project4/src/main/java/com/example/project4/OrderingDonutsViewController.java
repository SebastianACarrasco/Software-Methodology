package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OrderingDonutsViewController {
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

    /**
     * Initializes the combo box with the donut types
     */
    @FXML
    public void initialize() {
        baseButton.getItems().addAll("yeast", "cake", "donut holes");
    }

    /**
     * Receives data from previos view and sends it to the next view
     * @param event
     */
    @FXML
    public void receiveUserData(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();
        order.add(donut);
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
        printSubTotal();
    }

    @FXML
    public void getDonutQuantity(ActionEvent event) {
        donut.setQuantity(Integer.parseInt(this.donutsAmount.getText()));
        printSubTotal();
    }

    /**
     * Prints current subtotal to the gui
     */
    @FXML
    public void printSubTotal() {
        subtotalAmount.setText(String.format("%.2f", donut.itemPrice()));
    }

}
