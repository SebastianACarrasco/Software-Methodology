package com.example.project4;
import java.util.HashMap;

/**
 * Keeps the list of menu items added to the order as well as provides a
 * unique identifier for every order.
 *
 * @author Sebastian Carrasco
 */
public class Order implements Customizable {
    private static final double TAXRATE = 0.06625;
    private static int id = 1;
    private HashMap<Integer, MenuItem> order;

    /**
     * Constructor for Order
     */
    public Order() {
        id++;
        order = new HashMap<>();
    }

    /**
     * Adds a menu item to the order
     * @param o
     * @return true if the menu item was added, false otherwise
     */
    @Override
    public boolean add(Object o) {
        boolean added = false;

        if (o instanceof MenuItem) {
            order.put(id, (MenuItem) o);
            added = true;
        }
        return added;
    }

    /**
     * Removes a menu item from the order
     * @param obj
     * @return true if the menu item was removed, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        boolean removed = false;

        if (obj instanceof MenuItem) {
            order.remove(id);
            removed = true;
        }
        return removed;
    }

    /**
     * Helper method to calculate tax after subtotal of order
     * @return
     */
    private double taxes(double total) {
        return total * TAXRATE;
    }

    /**
     * Returns subtotal of the order. Gets itemPrice from each menu item
     * @return subtotal of the order
     */
    public double subTotal() {
        int total = 0;
        for (MenuItem item : order.values()) {
            total += item.itemPrice();
        }
        return taxes(total);
    }

    /**
     * Getter for the order id
     * @return order id
     */
    public int getId() {
        return this.id;
    }

    /**
     * toString method for Order. Prints the order thats stored in a hashmap
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : order.values()) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}


