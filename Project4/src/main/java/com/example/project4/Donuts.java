package com.example.project4;

import java.util.Locale;

/**
 *  Class for creating donut orders. There will be donut type, flavor, and quantity.
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public class Donuts extends MenuItem {
    private static final double YEAST = 1.59;
    private static final double CAKE = 1.79;
    private static final double HOLES = 0.39;
    private String donutType;
    private String flavor;
    private int quantity;
    private double total;

    /**
     * Constructor for creating donut order.
     */
    public Donuts() {
        donutType = "";
        flavor = "";
        quantity = 0;
        total = 0;
    }

    /**
     * Sets the order price with given type and quantity.
     * @return total price of the order
     */
    @Override
    public double itemPrice() {
        switch(this.donutType.toLowerCase()) {
            case "cake":
                total = CAKE * quantity;
                break;
            case "yeast":
                total = YEAST * quantity;
                break;
            case "holes":
                total = HOLES * quantity;
                break;
            default:
                total = 0;
        }

        return total;
    }

    /**
     * toString method for donut order.
     * @return string of donut order
     */
    @Override
    public String toString() {
        return "Donut: " + donutType + ", " + flavor + ", " + quantity;
                //+ ", $" + String.format("%.2f", itemPrice());
    }

    /**
     * Setter the donut type.
     */
    public void setDonutType(String donutType) {
        this.donutType = donutType;
    }

    /**
     * Getter for donut type.
     * @return donut type
     */
    public String getDonutType() {
        return donutType;
    }

    /**
     * Setter for flavor.
     */
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    /**
     * Getter for flavor.
     * @return flavor
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Setter for quantity.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    /**
     * Getter for quantity.
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }
}



