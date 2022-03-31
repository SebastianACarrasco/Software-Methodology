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
    private int ID; //set id value in controller and increment by 1 per order
    private ArrayList<MenuItem> items;

    /**
     * Constructor for Order
     */
    public Order() {
        this.ID = 0;
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
     * Setters for order id
     */
    public int setID(int id) {
        return this.ID = id;
    }

    /**
     * getter for order id
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
        sb.append(getID() + " ");

        double total = subTotalWithTax();
        for (MenuItem item : items) {
            sb.append(item.toString() + "\n");
        }
        sb.append("Subtotal w/ tax: $" + String.format("%.2f", total) + "\n");
        return sb.toString();
    }
}


