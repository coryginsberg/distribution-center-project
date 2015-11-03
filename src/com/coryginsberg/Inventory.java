package com.coryginsberg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
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

    public ArrayList<HashMap<Integer, String>> getInventory() {
        return inventory;
    }


    public String getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "City: " + getCity() + " | Inventory: " + getInventory().toString();
    }
}
