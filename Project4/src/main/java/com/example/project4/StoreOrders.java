package com.example.project4;
import javafx.scene.control.Menu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class that keeps list of orders placed by customers.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class StoreOrders implements Customizable {
    private HashMap<Integer, Order> storeOrders;

    /**
     * Constructor for StoreOrders.
     */
    public StoreOrders() {
        storeOrders = new HashMap<>();
    }

    /**
     * Getters for order ID
     * @return order ID
     */
    public int getID(Order order) {
        return order.getID();
    }

    /**
     * gets the store orders
     * @return a hashmap of the order ID and order
     */
    public HashMap<Integer, Order> getStoreOrders() {
        return storeOrders;
    }

    /**
     * Adds an order to the list of store orders .
     * @param o
     * @return true if the order is added, false otherwise.
     */
    @Override
    public boolean add(Object o) {
        boolean added = false;

        if (o instanceof Order) {
            storeOrders.put(((Order) o).getID(), (Order) o);
            added = true;
        }
        return added;
    }

    /**
     * Removes an order from the list of store orders.
     * @param o
     * @return true if the order is removed, false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        if(o instanceof Order) {
            if(storeOrders.containsValue(o)) {
                storeOrders.remove(((Order) o).getID());
                removed = true;
            }
        }
        return removed;
    }

    /**
     * toString that prints all the orders from the store.
     * @return String of all the orders.
     */
    @Override
    public String toString() {
        String s = "";
        for (Order o : storeOrders.values()) {
            s += String.format("%.2f", o.toString()) + "\n";
        }
        return s;
    }
}



