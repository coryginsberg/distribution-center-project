package com.coryginsberg;

import com.coryginsberg.managers.InventoryManager;
import com.coryginsberg.managers.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class ProcessOrder {
    private int time;
    private String id;
    private String destination;
    private String priority;
    private HashMap<Item, Integer> items = new HashMap<>();

    public ProcessOrder(Order order) {
        this.time = order.getTime();
        this.id = order.getID();
        this.destination = order.getDestination();
        this.priority = order.getPriority();
        this.items = order.getItems();
        items.forEach((item, amt) -> getFacilitiesWithItem(item));
    }

    private ArrayList<Facility> getFacilitiesWithItem(Item item) {
        ArrayList<Facility> facilitiesWithItem = new ArrayList<>();

        InventoryManager.getInventories().forEach(currentInventory -> currentInventory.getNondepletedInventory().forEach((itemID, amt) -> {
            if (itemID.equals(item.getId())) {
                NetworkManager.getFacilities().forEach(facility -> {
                    if (facility.getCity().equals(currentInventory.getCity())) {
                        facilitiesWithItem.add(facility);
                        System.out.println(facility);
                    }
                });
            }
        }));
        System.out.println();

        return facilitiesWithItem;
    }

    private int getShortestPathFromFacilities(/*ArrayList<Facility> facilitiesWithItem*/) {
//        facilitiesWithItem.forEach(facility -> {
//            NetworkManager.getFacilities().forEach(destinationFacility -> {
//                if (destinationFacility.getCity().equals(destination)) {
//                    GraphManager.getShortestPath(facility, destinationFacility);
//                }
//            });
//        });
        return 5; // FIXME: 11/17/15 Once the application is done, get to this (if there is time).
    }

}
