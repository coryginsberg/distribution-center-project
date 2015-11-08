package com.coryginsberg.factories;

import com.coryginsberg.Network;

import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class FacilityFactory {

    public static Network addFacility(String name, int rate, int cost, HashMap<Integer, String> linkedCities) {
        return new Network(name, rate, cost, linkedCities);
    }
}

