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
        total = 0;
    }

    /**
     * Method to add a coffee order.
     * @param o
     * @return true if order is added, false if not
     */
    @Override
    public boolean add(Object o) {
        boolean added = false;
        if(o instanceof String) {
            addIns.add((String)o);
            added = true;
        }
        return added;
    }

    /**
     * method to remove a coffee order.
     * @param o
     * @return true if order is removed, false if not
     */
    @Override
    public boolean remove(Object o) {
        boolean removed = false;
        if (o instanceof String) {
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
            case "tall":
                total += INITPRICE + (SIZEPRICESTEP * TALL);
                break;
            case "grande":
                total += INITPRICE + (SIZEPRICESTEP * GRANDE);
                break;
            case "venti":
                total += INITPRICE + (SIZEPRICESTEP * VENTI);
                break;
            default:
                total = 0;
        }

        //gets addin total based on arraylist of addins size
        total += addIns.size() * ADDINPRICE;

        return total;
    }

    /**
     * toString method for coffee order.
     * @return string of coffee order
     */
    @Override
    public String toString() {
        String order = "Coffee: " + this.size + ", ";

        for(int i = 0; i < addIns.size(); i++) {
            order += addIns.get(i) + ", ";
        }
        //order += "$" + String.format("%.2f", itemPrice());
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
     * Setter for addins.
     * @param addIns
     * @return true if addins is added, false if not
     */
    public boolean setAddIns(String addIns) {
        //this.addIns.add(addIns);
        return add(addIns);
    }

    /**
     * Removes addin
     * @param addIns
     * @return true if addin is removed, false if not
     */
    public boolean removeAddIns(String addIns) {
        return remove(addIns);
    }


    /**
     * Getter for coffee size
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     * Getter for the addins in the coffee order.
     * @return addins as an arraylist
     */
    public ArrayList<String> getAddIns() {
        return addIns;
    }
}


