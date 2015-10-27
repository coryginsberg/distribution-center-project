package com.coryginsberg.Managers;

import com.coryginsberg.Facility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/27/2015.
 * Created for Logistics Application.
 */
public class InventoryManager {

    public static InventoryManager inventoryManager = new InventoryManager();

    private static ArrayList<Facility> inventories = new ArrayList<>();

    public void addFacility(String name, int rate, int cost, ArrayList<HashMap<Integer, String>> linkedCities) {
        Facility facility = new Facility(name, rate, cost, linkedCities);
        inventories.add(facility);
    }

    public int getNumInventories() {
        return inventories.size();
    }

    public ArrayList<Facility> inventories() {
        return inventories;
    }

}
