package com.coryginsberg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/27/2015
 */

public class Inventory {

    private String city;
    private ArrayList<HashMap<Integer, String>> inventory;

    public Inventory(String city, ArrayList<HashMap<Integer, String>> inventory) {
        if (city == null) throw new RuntimeException("City does not exist.");
        this.city = city;
        if (inventory == null) throw new RuntimeException("Inventory does not exist.");
        this.inventory = inventory;
    }

    /**
     * Returns the current inventory of the facility
     *
     * @return Current facility inventory.
     */
    public ArrayList<HashMap<Integer, String>> getInventory() {
        return inventory;
    }

    /**
     * Returns the city requested.
     * @return The city that was requested.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Returns the Network and its inventory as a string.
     * @return The Network and its inventory as a string.
     */
    @Override
    public String toString() {
        return "City: " + getCity() + " | Inventory: " + getInventory().toString();
    }
}
