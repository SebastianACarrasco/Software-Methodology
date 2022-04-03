package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.lang.reflect.GenericArrayType;

public class OrderingCoffeeViewController {
    Coffee coffee = new Coffee();
    
    @FXML
    public ComboBox coffeeSize;
    @FXML
    public CheckBox creamAddIn;
    @FXML
    public CheckBox syrupAddIn;
    @FXML
    public CheckBox milkAddIn;
    @FXML
    public CheckBox caramelAddIn;
    @FXML
    public CheckBox whippedCreamAddIn;
    @FXML
    public TextField coffeeSubtotal;
    @FXML
    public Button addCoffeeOrder;

    /**
     * Initializes the combo box with the coffee sizes
     */
    @FXML
    public void initialize() {
        coffeeSize.getItems().addAll("short", "tall", "grande", "venti");
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
        order.setItems(coffee);
    }

    /**
     * Gets coffee size from gui and sets it to the coffee object
     * @param event
     */
    @FXML
    public void getCoffeeSize(ActionEvent event) {
        coffee.setSize(this.coffeeSize.getValue().toString());
        printSubTotal();
    }

    /**
     * Checks if add in combo boxes are checked and sets the add in to the coffee object
     * @param event
     */
    @FXML
    public void isChecked(ActionEvent event) {
        if(creamAddIn.isSelected()) {
            coffee.add("Cream");
        } else {
            coffee.remove("Cream");
        }
        if(syrupAddIn.isSelected()) {
            coffee.add("Syrup");
        } else {
            coffee.remove("Syrup");
        }
        if(milkAddIn.isSelected()) {
            coffee.add("Milk");
        }else {
            coffee.remove("Milk");
        }
        if(caramelAddIn.isSelected()) {
            coffee.add("Caramel");
        } else {
            coffee.remove("Caramel");
        }
        if(whippedCreamAddIn.isSelected()) {
            coffee.add("Whipped Cream");
        } else {
            coffee.remove("Whipped Cream");
        }

        printSubTotal();
    }

    /**
     * Prints current subtotal to the gui
     */
    @FXML
    public void printSubTotal() {
        coffeeSubtotal.setText(String.format("%.2f", coffee.itemPrice()));
    }


}
