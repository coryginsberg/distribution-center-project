package com.coryginsberg.managers;

import com.coryginsberg.Item;
import com.coryginsberg.Network;

import java.util.ArrayList;

/**
 * The Manager of the Network
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */
public class FacilityManager {
    private static ArrayList<Network> facilities = new ArrayList<>();
    private static ArrayList<Item> storableItems = new ArrayList<>();

    public static void addFacility(Network network) {
        facilities.add(network);
    }

    public static int getNumFacilities() {
        return facilities.size();
    }

    public static ArrayList<Network> getFacilities() {
        return facilities;
    }

    public static void addStorableItem(Item item) {
        storableItems.add(item);
    }

    public static ArrayList<Item> getStorableItems() {
        return storableItems;
    }


}