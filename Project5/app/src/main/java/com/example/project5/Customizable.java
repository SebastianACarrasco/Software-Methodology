package com.example.project5;

/**
 * Interface class for adding and removing either from an order or toppings
 *
 * @author Sebastian Carrasco, Rachael Chin
 */
public interface Customizable {
    /**
     * general method for adding to menu
     * @param obj
     * @return true if added, false if not
     */
    boolean add(Object obj);

    /**
     * general method for removing from menu
     * @param obj
     * @return true if removed, false if not
     */
    boolean remove(Object obj);
}
