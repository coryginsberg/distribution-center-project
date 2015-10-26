package com.coryginsberg.Managers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class FacilityManager {
    public static FacilityManager facilityManager = new FacilityManager();

    private static ArrayList<Facility> facilities = new ArrayList<>();

    public void addFacility(String name, int rate, int cost, ArrayList<HashMap<Integer, String>> linkedCities) {
        Facility facility = new Facility(name, rate, cost, linkedCities);
        System.out.println(facility.toString());
        facilities.add(facility);
    }

    public int getNumFacilities() {
        return facilities.size();
    }

    public ArrayList<Facility> facilities() {
        return facilities;
    }


}