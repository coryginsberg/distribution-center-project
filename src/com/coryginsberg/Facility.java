package com.coryginsberg;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class Facility {
    private String name;
    private float rate;
    private int cost; // While cost is the same for all facilities currently, it is possible for cost to change in the future.
    private ArrayList<HashMap<Integer, String>> network;

    public Facility(String name, float rate, int cost, ArrayList<HashMap<Integer, String>> connectingCities) {
        this.name = name;
        this.rate = rate;
        this.cost = cost;
        this.network = connectingCities;
    }

    public String getCity() {
        return this.name;
    }

    public float getRate() {
        return this.rate;
    }

    public int getCost() {
        return this.cost;
    }

    public ArrayList<HashMap<Integer, String>> getConnectingCities() {
        return this.network;
    }

    @Override
    public String toString() {
        return "City: " + getCity() + ", Rate: " + getRate() + ", Cost:" + getCost() + ", Connected Cities: " + getConnectingCities() + ".";
    }

}
