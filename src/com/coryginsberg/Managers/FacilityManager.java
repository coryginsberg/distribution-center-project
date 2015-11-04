package com.coryginsberg.managers;

import com.coryginsberg.Facility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The Manager of the Facility
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */
public class FacilityManager {
    public static FacilityManager facilityManager = new FacilityManager();

    private static ArrayList<Facility> facilities = new ArrayList<>();

    public void addFacility(String name, int rate, int cost, ArrayList<HashMap<Float, String>> linkedCities) {
        Facility facility = new Facility(name, rate, cost, linkedCities);
        facilities.add(facility);
    }

    public int getNumFacilities() {
        return facilities.size();
    }

    public ArrayList<Facility> facilities() {
        return facilities;
    }

}