package com.example.project4;
import java.io.FileWriter;
import java.util.HashMap;

/**
 * Class that keeps list of orders placed by customers.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class StoreOrders implements Customizable {
    private int ID;
    private HashMap<Integer, Order> storeOrders;

    /**
     * Constructor for StoreOrders.
     */
    public StoreOrders() {
        ID = 0;
        storeOrders = new HashMap<>();
    }

    /**
     * Stores an order into the hashmap with unique ID
     * @param o
     */
    public void ordersPlaced(Order o) {
        storeOrders.put(this.ID++, o);
    }

    /**
     * Adds an order to the list of store orders .
     * @param o
     * @return true if the order is added, false otherwise.
     */
    @Override
    public boolean add(Object o) {
        return false;
    }

    /**
     * Removes an order from the list of store orders.
     * @param o
     * @return true if the order is removed, false otherwise.
     */
    @Override
    public boolean remove(Object o) {
        return false;
    }

    /**
     * Exports all the orders to a file.
     */
    public void exportStoreOrders() {}

    /**
     * toString that prints all the orders from the store.
     * @return String of all the orders.
     */
    @Override
    public String toString() {
        String s = "";
        for (Order o : storeOrders.values()) {
            s += ID + " " + o.toString() + "\n";
        }
        return s;
    }





    //Testbed
    public static void main(String[] args) {
        //order 1
        Order order = new Order();

        Coffee item = new Coffee();
        item.setSize("Venti");
        item.setAddIns("Cream");
        item.setAddIns("Whipped Cream");

        Donuts item2 = new Donuts();
        item2.setDonutType("yeast");
        item2.setFlavor("strawberry");
        item2.setQuantity(5);

        order.setItems(item);
        order.setItems(item2);

        //order 2
        Order order2 = new Order();

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
    }
}



