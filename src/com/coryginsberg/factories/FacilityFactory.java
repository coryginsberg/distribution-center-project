package com.coryginsberg.factories;

import com.coryginsberg.Facility;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class FacilityFactory {

    public static Facility addFacility(String name, int rate, int cost, ArrayList<HashMap<Integer, String>> linkedCities) {
        return new Facility(name, rate, cost, linkedCities);
    }
}

