package com.coryginsberg.factories;

import com.coryginsberg.Inventory;
import com.coryginsberg.managers.InventoryManager;

import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class InventoryFactory {
    public static Inventory addInventory(String city, HashMap<String, Integer> items) {

        Inventory newInventory = new Inventory(city, items);
        InventoryManager.addInventory(newInventory);
        return newInventory;
    }
}
