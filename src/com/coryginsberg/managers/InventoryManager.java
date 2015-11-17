package com.coryginsberg.managers;

import com.coryginsberg.Inventory;

import java.util.ArrayList;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/27/2015
 */
public class InventoryManager {

    private static ArrayList<Inventory> inventories = new ArrayList<>();

    public static void addInventory(Inventory inventory) {
        inventories.add(inventory);
    }

    public static ArrayList<Inventory> getInventories() {
        return inventories;
    }

}
