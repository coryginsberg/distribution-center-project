package com.coryginsberg.managers;

import com.coryginsberg.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/27/2015
 */
public class InventoryManager {

    public static InventoryManager inventoryManager = new InventoryManager();

    private static ArrayList<Inventory> inventories = new ArrayList<>();

    public void addInventory(String city, ArrayList<HashMap<Integer, String>> items) {
        Inventory inventory = new Inventory(city, items);
        inventories.add(inventory);
    }

    public int getNumInventories() {
        return inventories.size();
    }

    public ArrayList<Inventory> inventories() {
        return inventories;
    }

}
