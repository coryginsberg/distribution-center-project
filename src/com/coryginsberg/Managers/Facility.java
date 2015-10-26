package com.coryginsberg.Managers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class Facility {
    private String name;
    private int rate;
    private int cost; // While cost is the same for all facilities currently, it is possible for cost to change in the future.
    private ArrayList<HashMap<Integer, String>> network;

    public Facility(String name, int rate, int cost, ArrayList<HashMap<Integer, String>> connectingCities) {
        this.name = name;
        this.rate = rate;
        this.cost = cost;
        this.network = connectingCities;
    }

    public String getCity() {
        return this.name;
    }

    public int getRate() {
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

