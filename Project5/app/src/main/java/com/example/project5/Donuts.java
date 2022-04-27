package com.example.project5;

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
     * Constructor for creating a donut given the parameters
     * @param type
     * @param flavor
     * @param quantity
     */
    public Donuts(String type, String flavor, int quantity) {
        this.donutType = type;
        this.flavor = flavor;
        this.quantity = quantity;
        total = itemPrice();
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
        return "Donut: " + donutType + ", " + flavor;
    }

}



