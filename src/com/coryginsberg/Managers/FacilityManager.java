package com.coryginsberg.Managers;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class FacilityManager {

    static int numFacilities = 0;

    public FacilityManager(String name, int rate, int cost, ArrayList<HashMap<Integer, String>> linkedCities) {
        Facility facility = new Facility(name, rate, cost, linkedCities);
        System.out.println(facility.toString());
        numFacilities++;
    }

}
