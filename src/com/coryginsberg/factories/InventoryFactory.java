package com.coryginsberg.factories;

import com.coryginsberg.Inventory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class InventoryFactory {
    public static Inventory addInventory(String city, ArrayList<HashMap<Integer, String>> items) {
        return new Inventory(city, items);
    }
}
