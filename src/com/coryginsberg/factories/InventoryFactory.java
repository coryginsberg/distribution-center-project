package com.coryginsberg.factories;

import com.coryginsberg.Inventory;

import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class InventoryFactory {
    public static Inventory addInventory(String city, HashMap<Integer, String> items) {

        HashMap<String, Integer> reverseHash = new HashMap<>();
        reverseHash.put(items.values().toString(), Integer.getInteger(items.keySet().toString()));
        return new Inventory(city, reverseHash);
    }
}
