package com.example.project4;
import java.util.ArrayList;

/**
 * Keeps the list of menu items added to the order as well as provides a
 * unique identifier for every order.
 *
 * @author Sebastian Carrasco
 */
public class Order implements Customizable {
    private static final double TAXRATE = 0.06625;
    //private static int id = 0;
    private ArrayList<MenuItem> items;

    /**
     * Constructor for Order
     */
    public Order() {
        //id++; //increment id each time a new order is created
        items = new ArrayList<>();
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
            items.add((MenuItem) o);
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
            if(items.contains(obj)) {
                items.remove(obj);
                removed = true;
            }
        }
        return removed;
    }

    /**
     * Returns subtotal of the order with tax. Gets itemPrice from each menu item
     * @return subtotal of the order
     */
    public double subTotalWithTax() {
        double subTotal = 0;
        for (MenuItem item : items) {
            subTotal += item.itemPrice();
        }
        return subTotal * TAXRATE;
    }

    /**
     * Getter for the arraylist which contains all the menu items.
     * @return arraylist of menu items
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds a menu item to the order
     * @param items
     */
    public void setItems(MenuItem items) {
        this.items.add(items);
    }

    /**
     * toString method for Order. Prints the order
     * @return string representation of the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        double total = subTotalWithTax();
        sb.append(String.format("%.2f", total) + " ");
        for (MenuItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}


