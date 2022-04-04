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


    public void goToCoffee(Order order) throws Exception {
        coffee.setTotal(this.coffeeTotal);
        order.setItems(coffee);
        //resetOrder();
        //this.coffeeSubtotal.setText("");
        //this.coffeeTotal = 0;
    }

    /**
     * Receives data from previous view and sends it to the next view
     * @param event
     */
    /*
    public void receiveUserData(ActionEvent event) {
        //this should be onloaded
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();
        coffee.setTotal(this.coffeeTotal);
        order.setItems(coffee);
        resetOrder();
        this.coffeeSubtotal.setText("");
        this.coffeeTotal = 0;
    }
    */

    @FXML
    public void sendToBasket(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("orderingCoffeeView.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("OrderBasketView.fxml")));
            stage.setUserData(coffee);
            stage.setTitle("Order Basket");
            stage.setScene(new Scene(root));
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
        resetOrder();
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




