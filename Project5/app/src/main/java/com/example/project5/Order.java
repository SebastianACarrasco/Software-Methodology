package com.example.project5;
import java.util.ArrayList;

/**
 * Keeps the list of menu items added to the order as well as provides a
 * unique identifier for every order.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class Order implements Customizable {
    private static final double TAXRATE = 0.06625;
    private static int ID;
    private ArrayList<MenuItem> items;

    /**
     * Constructor for Order
     */
    public Order() {
        this.ID++;
        this.items = new ArrayList<>();
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
     * Gets the order's tax value for NJ
     * @return double
     */
    public double getTaxes() {
        double subTotal = 0;
        for (MenuItem item : items) {
            subTotal += item.itemPrice();
        }
        return subTotal * TAXRATE;
    }

    /**
     * removes an item from the list of orders
     * @param items
     */
    public void removeItem(ArrayList<MenuItem> items) {
        for(int i = 0; i < items.size(); i++) {
            if(this.items.contains(items.get(i))) {
                this.items.remove(i);
                return;
            }
        }
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
        subTotal = subTotal * (1 + TAXRATE);
        return subTotal;
    }

    /**
     * Returns the total of the order without tax
     * @return total of the order without tax
     */
    public double subTotal() {
        double subTotal = 0;
        for (MenuItem item : items) {
            subTotal += item.itemPrice();
        }
        return subTotal;

    }

    /**
     * getter for order id
     * @return int ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Getter for the arraylist which contains all the menu items.
     * @return arraylist of menu items
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * toString method for Order. Prints the order
     * @return string representation of the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : items) {
            sb.append(item.toString() + "\n");
        }
        return sb.toString();
    }
}


