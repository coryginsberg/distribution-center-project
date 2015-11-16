package com.coryginsberg.factories;

import com.coryginsberg.Facility;
import com.coryginsberg.managers.NetworkManager;

/**
 * Created by Cory Ginsberg on 11/15/15.
 * Created for Logistics Application.
 */
public class FacilityFactory {

    public static Facility addFacility(String name, int rate, int cost) {
        Facility newFacility = new Facility(name, rate, cost);
        NetworkManager.addFacility(newFacility);
        return newFacility;
    }
}
