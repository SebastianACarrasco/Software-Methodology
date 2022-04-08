package com.example.project4;
import java.util.ArrayList;

/**
 * Class for creating a coffee order and storing the order in an ArrayList.
 * Object will store size, addins, and total cost.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class Coffee extends MenuItem implements Customizable{
    private String size;
    private double total;
    private ArrayList<String> addIns;
    private static final double ADDINPRICE = 0.3;
    private static final double INITPRICE = 1.69;
    private static final int TALL = 1;
    private static final int GRANDE = 2;
    private static final int VENTI = 3;
    private static final double SIZEPRICESTEP = 0.4;

    /**
     * Constructor for Coffee order.
     */
    public Coffee() {
        addIns = new ArrayList<String>();
        size = "";
        this.total = 0;
    }

    /**
     * Method to add a coffee add in.
     * @param o
     * @return true if order is added, false if not
     */
    @Override
    public boolean add(Object o) {
        boolean added = false;
        if(o instanceof String) {
            this.total += ADDINPRICE;
            addIns.add((String)o);
            added = true;
        }
        return added;
    }

    /**
     * method to remove a coffee add in.
     * @param o
     * @return true if order is removed, false if not
     */
    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        if (o instanceof String) {
            this.total -= ADDINPRICE;
            addIns.remove(o);
            removed = true;
        }
        return removed;
    }

    /**
     * Sets the order price with given size and addins.
     * @return total price of the order
     */
    @Override
    public double itemPrice() {
        switch(this.size.toLowerCase()) {
            case "short":
                this.total = INITPRICE;
                break;
            case "tall":
                this.total = INITPRICE + (SIZEPRICESTEP * TALL);
                break;
            case "grande":
                this.total = INITPRICE + (SIZEPRICESTEP * GRANDE);
                break;
            case "venti":
                this.total = INITPRICE + (SIZEPRICESTEP * VENTI);
                break;
            default:
                this.total = 0;
        }

        if(addIns.size() != 0) {
            for (int i = 0; i < addIns.size(); i++) {
                this.total += ADDINPRICE;
            }
        }
        return this.total;
    }


    /**
     * toString method for coffee order.
     * @return string of coffee order
     */
    @Override
    public String toString() {
        String order = "Coffee: " + this.size + ", ";
        order += "Add-ins: ";
        if(addIns.size() == 0) {
            order += "None";
        }
        for(int i = 0; i < addIns.size(); i++) {
            order += addIns.get(i) + ", ";
        }
        return order;
    }

    /**
     * Getter for the size of the coffee.
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Getter for coffee size
     * @return size of coffee
     */
    public String getSize() {
        return this.size;
    }

    /**
     * Setter for total of coffee order
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Getter for total of coffee order with addins
     * @return total of coffee order
     */
    public double getCoffeeAddInTotal() {
        if(addIns.size() != 0) {
            for (int i = 0; i < addIns.size(); i++) {
                this.total += ADDINPRICE;
            }
        }
        return this.total;
    }

}


