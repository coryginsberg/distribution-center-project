package com.coryginsberg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/27/2015.
 * Created for Logistics Application.
 */
public class Inventory {

    private String city;
    private ArrayList<HashMap<Integer, String>> inventory;

    public Inventory(String city, ArrayList<HashMap<Integer, String>> inventory) {
        this.city = city;
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
