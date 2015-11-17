package com.coryginsberg.managers;

import com.coryginsberg.Facility;
import com.coryginsberg.Item;

import java.util.ArrayList;

/**
 * The Manager of the Network
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class NetworkManager {
    private static ArrayList<Facility> facilities = new ArrayList<>();
    private static ArrayList<Item> storableItems = new ArrayList<>();

    public static void addFacility(Facility facility) {

        if (!facilities.contains(facility)) {
            facilities.add(facility);
        }

    }

    public static int getNumFacilities() {
        return facilities.size();
    }

    public static ArrayList<Facility> getFacilities() {
        return facilities;
    }

    public static void addStorableItem(Item item) {
        storableItems.add(item);
    }

    public static ArrayList<Item> getStorableItems() {
        return storableItems;
    }
}
