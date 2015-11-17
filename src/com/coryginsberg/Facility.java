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
    private int rate;
    private int cost;
    private HashMap<Facility, Integer> connectingCities = new HashMap<>();
    private ArrayList<Integer> dailyRate = new ArrayList<>();

    public Facility(String name, int rate, int cost) {
        this.city = name;
        this.rate = rate;
        this.cost = cost;
        for (int i = 0; i <= 12; i++) { // Start off with 12 days.
            dailyRate.add(rate);
        }
    }

    public void addConnectingCity(HashMap<Facility, Integer> connectedFacility) {
        connectedFacility.forEach((city, distance) -> NetworkManager.getFacilities().forEach(facility -> {
            if (facility.equals(city)) {
                this.connectingCities.put(facility, distance);
            }
        }));
    }

    public String getCity() {
        return city;
    }

    public int getRate() {
        return rate;
    }

    public int getCost() {
        return cost;
    }

    public HashMap<Facility, Integer> getConnectingCities() {
        return connectingCities;
    }

    public ArrayList<Integer> getDailyRate() {
        return dailyRate;
    }


    @Override
    public String toString() {
        return getCity() + ", At Rate: " + getRate() + ", At Cost: " + getCost();
    }
}
