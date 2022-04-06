package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.lang.reflect.GenericArrayType;
import java.util.Objects;

public class OrderingCoffeeViewController {
    private static final double ADDINPRICE = 0.3;
    private double coffeeTotal = 0;
    private Coffee coffee;
    private mainViewController controller;
    
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
        coffee = new Coffee();
    }

    public void setMainController(mainViewController controller) {
        this.controller = controller;
    }

    @FXML
    public void sendToBasket(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderBasketView.fxml"));
            Parent root = loader.load();

            controller.getOrder().add(coffee);
            OrderBasketViewController basket = loader.getController();
            basket.setMainController(controller);

            Stage stage = new Stage();
            stage.setTitle("Order Basket");
            Scene basketScene = new Scene(root);
            stage.setScene(basketScene);
            resetOrder();
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets coffee size from gui and sets it to the coffee object
     * @param event
     */
    @FXML
    public void getCoffeeSize(ActionEvent event) {
        coffee.setSize(this.coffeeSize.getValue().toString());
        this.coffeeTotal = coffee.itemPrice();
        printSubTotal();
    }

    /**
     * Sets gui inputs to default values
     */
    private void resetOrder() {
        creamAddIn.setSelected(false);
        syrupAddIn.setSelected(false);
        milkAddIn.setSelected(false);
        caramelAddIn.setSelected(false);
        whippedCreamAddIn.setSelected(false);
        coffee = new Coffee();
    }

    /**
     * Adds or removes cream to the coffee object
     * @param event
     */
    @FXML
    public void cream(ActionEvent event) {
        if(creamAddIn.isSelected()) {
            coffee.add("Cream");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Cream");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    /**
     * Adds or removes syrup from order
     * @param event
     */
    @FXML
    public void syrup(ActionEvent event) {
        if(syrupAddIn.isSelected()) {
            coffee.add("Syrup");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Syrup");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    /**
     * Adds or removes milk from order
     * @param event
     */
    @FXML
    public void milk(ActionEvent event) {
        if(milkAddIn.isSelected()) {
            coffee.add("Milk");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Milk");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    /**
     * Adds or removes caramel from order
     * @param event
     */
    @FXML
    public void caramel(ActionEvent event) {
        if(caramelAddIn.isSelected()) {
            coffee.add("Caramel");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Caramel");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    /**
     * Adds or removes whipped cream from order
     * @param event
     */
    @FXML
    public void whippedCream(ActionEvent event) {
        if(whippedCreamAddIn.isSelected()) {
            coffee.add("Whipped Cream");
            coffeeTotal += ADDINPRICE;
        } else {
            coffee.remove("Whipped Cream");
            coffeeTotal -= ADDINPRICE;
        }
        printSubTotal();
    }

    /**
     * Prints current subtotal to the gui
     */
    @FXML
    public void printSubTotal() {
        coffeeSubtotal.setText(String.format("%.2f", this.coffeeTotal));
    }


}




