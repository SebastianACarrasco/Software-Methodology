package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderingDonutsViewController {
    Donuts donut = new Donuts();

    @FXML
    public ComboBox baseButton;
    @FXML
    public CheckBox strawberryFlavor;
    @FXML
    public CheckBox plainFlavor;
    @FXML
    public CheckBox berriesFlavor;
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
        order.setItems(donuts);
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

    /**
     * Checks if add in combo boxes are checked and sets the add in to the donut object
     * @param event
     */
    @FXML
    public void isChecked(ActionEvent event) {
        if(strawberryFlavor.isSelected()) {
            donut.add("Strawberry");
        } else {
            donut.remove("Strawberry");
        }
        if(plainFlavor.isSelected()) {
            donut.add("Plain");
        } else {
            donut.remove("Plain");
        }
        if(berriesFlavor.isSelected()) {
            donut.add("Berries");
        }else {
            donut.remove("Berries");
        }
        printSubTotal();
    }

    @FXML
    public void getDonutQuantity(ActionEvent event) {
        donut.setQuantity(this.donutsAmount.getValue().toString());
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
