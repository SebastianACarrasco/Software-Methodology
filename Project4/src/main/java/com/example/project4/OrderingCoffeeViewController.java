package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class OrderingCoffeeViewController {
    private static final double ADDINPRICE = 0.3;
    private double coffeeTotal = 0;
    private Coffee coffee;
    private MainViewController controller;
    
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

    public void setMainController(MainViewController controller) {
        this.controller = controller;
    }


    @FXML
    public void sendToBasket(ActionEvent event) {
        try {
            if(!coffee.getSize().equals("")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("orderBasketView.fxml"));
                Parent root = loader.load();

                this.coffee.setTotal(coffeeTotal);
                this.controller.getOrder().add(this.coffee);
                OrderBasketViewController basket = loader.getController();
                basket.setMainController(controller);

                Stage stage = new Stage();
                stage.setTitle("Order Basket");
                Scene basketScene = new Scene(root);
                stage.setScene(basketScene);
                coffee = new Coffee();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("Coffee order has been made");
                alert.setContentText("You can close this alert and continue to the basket.");
                alert.showAndWait();
                resetOrder();
                stage.show();
            } else {
                emptyCoffee();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void emptyCoffee() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning!");
        alert.setHeaderText("Order is not complete!");
        alert.setContentText("Please fill out all entities before placing order.");
        alert.showAndWait();
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
        coffeeSize.setValue("");
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




