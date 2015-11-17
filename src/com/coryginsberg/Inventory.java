package com.coryginsberg;

import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/27/2015
 */

public class Inventory {

    private String city;
    private HashMap<String, Integer> inventory = new HashMap<>();

    public Inventory(String city, HashMap<String, Integer> inventory) {
        if (city == null) throw new RuntimeException("City does not exist.");
        this.city = city;
        if (inventory == null) throw new RuntimeException("Inventory does not exist.");
        this.inventory = inventory;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public HashMap<String, Integer> getDepletedInventory() {
        HashMap<String, Integer> depletedItems = new HashMap<>();
        inventory.forEach((item, amount) -> {
            if (amount == 0) {
                depletedItems.put(item, amount);
            }
        });
        return depletedItems;
    }

    public HashMap<String, Integer> getNondepletedInventory() {
        HashMap<String, Integer> nondepletedItems = new HashMap<>();
        inventory.forEach((item, amount) -> {
            if (amount > 0) {
                nondepletedItems.put(item, amount);
            }
        });
        return nondepletedItems;
    }

    public String getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "City: " + getCity() + " | Inventory: " + getInventory().toString();
    }
}
