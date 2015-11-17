package com.coryginsberg;

import com.coryginsberg.managers.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 11/15/15.
 * Created for Logistics Application.
 */
public class Facility {
    private String city;
    private float rate;
    private int cost;
    private HashMap<Facility, Integer> connectingCities = new HashMap<>();
    private ArrayList<Item> itemsInStock;

    public Facility(String name, float rate, int cost) {
        this.city = name;
        this.rate = rate;
        this.cost = cost;
    }

    public void addConnectingCity(HashMap<Facility, Integer> connectedFacility) {
        connectedFacility.forEach((city, distance) -> NetworkManager.getFacilities().forEach(facility -> {
            if (facility.equals(city)) {
                this.connectingCities.put(facility, distance);
            }
        }));
    }

    public void addInventory(Item item) {
        itemsInStock.add(item);
    }

    public String getCity() {
        return city;
    }

    public float getRate() {
        return rate;
    }

    public int getCost() {
        return cost;
    }

    public HashMap<Facility, Integer> getConnectingCities() {
        return connectingCities;
    }

    public ArrayList<Item> getItemsInStock() {
        return itemsInStock;
    }

    public boolean hasItem(Item item) {
        return itemsInStock.contains(item);
    }
    @Override
    public String toString() {
        return getCity() + ", At Rate: " + getRate() + ", At Cost: " + getCost() + ", Connected To: " + getConnectingCities() + ", Has Items: " + getItemsInStock();
    }
}
