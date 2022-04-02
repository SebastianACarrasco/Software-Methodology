package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class OrderingCoffeeViewController {
    private String[] addIns = new String[5];
    
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


    @FXML
    public void receiveUserData(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();
        Coffee coffee = new Coffee();
        
        coffee.setSize(getCoffeeSize(event));

        for(int i = 0; i < addIns.length; i++) {
            if(addIns[i] != null) {
                coffee.setAddIns(addIns[i]);
            }
        }

        order.setItems(coffee);
    }

    @FXML
    public String getCoffeeSize(MouseEvent event) {
        String coffeeSize = (String) this.coffeeSize.getValue();
        return coffeeSize;
    }

    @FXML
    public String[] getAddIns(MouseEvent event) {
        if(creamAddIn.isSelected()) {
            addIns[0] = "Cream";
        }
        if(syrupAddIn.isSelected()) {
            addIns[1] = "Syrup";
        }
        if(milkAddIn.isSelected()) {
            addIns[2] = "Milk";
        }
        if(caramelAddIn.isSelected()) {
            addIns[3] = "Caramel";
        }
        if(whippedCreamAddIn.isSelected()) {
            addIns[4] = "Whipped Cream";
        }
        return addIns;
    }

}
