package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class OrderBasketViewController {
    private static int ID;
    private static final double TAXRATE = 0.06625;

    @FXML
    public ListView basketListView;
    @FXML
    public TextArea basketSubtotal;
    @FXML
    public TextArea basketSalesTax;
    @FXML
    public TextArea basketOrderTotal;
    @FXML
    public TextArea basketViewOrderNumber;


    public OrderBasketViewController() {
        this.ID++;

    }

    /**
     * Receives data from previous view and sends it to the next view
     * @param event
     */
    @FXML
    public void receiveUserData(ActionEvent event) {
        //this should be onloaded
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();
        order.setID(ID);
        basketListView.getItems().add(order.toString());
        basketViewOrderNumber.setText(Integer.toString(order.getID()));
        basketSubtotal.setText(""+order.subTotal());
        //basketSalesTax.setText(""+order.salesTax());
        basketOrderTotal.setText(""+order.subTotalWithTax());
    }

    /*
    @FXML
    public void receiveOrderBasket(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Order order = (Order) stage.getUserData();

        StoreOrders storeOrders = new StoreOrders();
        storeOrders.ordersPlaced(order);
    }
     */
}
