package com.coryginsberg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/27/2015.
 * Created for Logistics Application.
 */
public class Inventory {

    private String city;
    private int rate;
    private int cost; // While cost is the same for all facilities currently, it is possible for cost to change in the future.
    private ArrayList<HashMap<Integer, String>> inventory;

    public Inventory(String city, int rate, int cost, ArrayList<HashMap<Integer, String>> connectingCities) {
        this.city = city;
        this.rate = rate;
        this.cost = cost;
    }

    public ArrayList<HashMap<Integer, String>> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<HashMap<Integer, String>> inventory) {
        this.inventory = inventory;
    }

    public String getCity() {
        return this.city;
    }

    public int getRate() {
        return this.rate;
    }

    public int getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return "City: " + getCity() + ", Rate: " + getRate() + ", Cost:" + getCost() + ", Connected Cities: " + ".";
    }

}
