package com.coryginsberg;

import java.util.HashMap;
import java.util.Map;

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

    public int itemInStock(Item item) {
        int stock = 0;
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            if (entry.getKey().equals(item.getId())) stock = entry.getValue();
        }

        return stock;
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
