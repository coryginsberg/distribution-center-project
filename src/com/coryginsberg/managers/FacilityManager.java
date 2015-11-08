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

public class FacilityManager implements Manager<Network> {
    private static ArrayList<Network> facilities = new ArrayList<>();
    private static ArrayList<Item> storableItems = new ArrayList<>();

    public void add(Network network) {
        facilities.add(network);
    }

    public int getNumFacilities() {
        return facilities.size();
    }

    public ArrayList<Network> get() {
        return facilities;
    }

    public void addStorableItem(Item item) {
        storableItems.add(item);
    }

    public ArrayList<Item> getStorableItems() {
        return storableItems;
    }


}