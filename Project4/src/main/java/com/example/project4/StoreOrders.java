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
     * Exports all the orders to a file.
     */
    public boolean exportStoreOrders() {
        boolean exported = false;
            File file = new File("storeOrders.txt");
            BufferedWriter bf = null;
            try {
                bf = new BufferedWriter(new FileWriter(file));
                for (HashMap.Entry<Integer, Order> entry : storeOrders.entrySet()) {
                    // put key and value separated by a colon
                    bf.write("" + entry.getValue());
                    bf.newLine();
                }
                bf.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return exported;
    }

    /**
     * toString that prints all the orders from the store.
     * @return String of all the orders.
     */
    @Override
    public String toString() {
        String s = "";
        for (Order o : storeOrders.values()) {
            s += o.toString() + "\n";
        }
        return s;
    }

    /*
    //Testbed
    public static void main(String[] args) {
        //order 1
        Order order = new Order();
        order.setID(1);

        Coffee item = new Coffee();
        item.setSize("Venti");
        item.setAddIns("Cream");
        item.setAddIns("Whipped Cream");

        Donuts item2 = new Donuts();
        item2.setDonutType("yeast");
        item2.setFlavor("strawberry");
        item2.setQuantity(5);

        Donuts item3 = new Donuts();
        item3.setDonutType("cake");
        item3.setFlavor("chocolate");
        item3.setQuantity(3);

        order.setItems(item);
        order.setItems(item2);
        order.setItems(item3);

        //order 2
        Order order2 = new Order();
        order2.setID(2);

        Coffee itemOrder2 = new Coffee();
        itemOrder2.setSize("grande");
        itemOrder2.setAddIns("caramel");
        itemOrder2.setAddIns("syrup");
        itemOrder2.setAddIns("Whipped Cream");

        Donuts dOrder2 = new Donuts();
        dOrder2.setDonutType("cake");
        dOrder2.setFlavor("plain");
        dOrder2.setQuantity(1);

        order2.setItems(itemOrder2);
        order2.setItems(dOrder2);

        //print storeOrders
        StoreOrders storeOrders = new StoreOrders();
        storeOrders.ordersPlaced(order);
        storeOrders.ordersPlaced(order2);

        System.out.println(storeOrders.toString());

        //export storeOrders
        //storeOrders.exportStoreOrders();
    }

     */
}



