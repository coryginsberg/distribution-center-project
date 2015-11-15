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
    private HashMap<String, Integer> inventory;

    public Inventory(String city, HashMap<String, Integer> inventory) {
        if (city == null) throw new RuntimeException("City does not exist.");
        this.city = city;
        if (inventory == null) throw new RuntimeException("Inventory does not exist.");
        this.inventory = inventory;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }


    public void addInventoryAmount(String itemToChange, int amount) {
        if (hasItem(itemToChange)) {
            inventory.merge(itemToChange, amount, Integer::sum);
        }
    }

    public void subtractInventoryAmount(String itemToChange, int amount) {
        if (hasItem(itemToChange)) {
            inventory.merge(itemToChange, amount, (integer, integer2) -> {
                integer = inventory.get(itemToChange);
                integer2 = amount;
                if (integer > integer2) return integer - integer2;
                return 0;
            });
        }
    }

    public ArrayList<String> getDepletedInventory() {
        ArrayList<String> depletedItems = new ArrayList<>();
        inventory.forEach((item, amount) -> {
            if (amount == 0) {
                depletedItems.add(item);
            }
        });
        return depletedItems;
    }

    public HashMap<String, Integer> getNondepletedInventory() {

        return inventory;
    }

    public boolean hasItem(String item) {
        return inventory.containsKey(item);
    }


    public String getCity() {
        return this.city;
    }

    @Override
    public String toString() {
        return "City: " + getCity() + " | Inventory: " + getInventory().toString();
    }
}
