package com.coryginsberg.managers;

import com.coryginsberg.Facility;

import java.util.ArrayList;

/**
 * The Manager of the Facility
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */
public class FacilityManager {
    private static ArrayList<Facility> facilities = new ArrayList<>();

    public static void addFacility(Facility facility) {
        facilities.add(facility);
    }

    public static int getNumFacilities() {
        return facilities.size();
    }

    public static ArrayList<Facility> facilities() {
        return facilities;
    }

}