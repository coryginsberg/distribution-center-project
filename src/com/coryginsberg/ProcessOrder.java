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

    private ArrayList<Integer> arrivalDays = new ArrayList<>();

    public ProcessOrder(Order order) {
        this.time = order.getTime();
        this.id = order.getID();
        this.destination = order.getDestination();
        this.priority = order.getPriority();
        this.items = order.getItems();
        items.forEach((item, amt) -> {
            getFacilitiesWithItem(item);
        });
    }

    private ArrayList<Facility> getFacilitiesWithItem(Item item) {
        ArrayList<Facility> facilitiesWithItem = new ArrayList<>();

        InventoryManager.getInventories().forEach(currentInventory -> currentInventory.getNondepletedInventory().forEach((itemID, amt) -> {
            if (itemID.equals(item.getId())) {
                NetworkManager.getFacilities().forEach(facility -> {
                    if (facility.getCity().equals(currentInventory.getCity())) {
                        facilitiesWithItem.add(facility);
                    }
                });
            }
        }));

        return facilitiesWithItem;
    }

    public HashMap<Facility, Integer> getNumItemsFromFacility(ArrayList<Facility> facilities) {
        facilities.forEach(facility -> {
            InventoryManager.getInventories().forEach(inventory -> {
                if (inventory.getCity().equals(facility.getCity())) {
                    //items.forEach();
                    //inventory.itemInStock()
                }
            });

        });
        return null;
    }
//    public ArrayList<String> getShortestPathFromFacilities(ArrayList<Facility> facilitiesWithItem) {
//
//        ArrayList<String> shortestPaths = new ArrayList<>();
//        facilitiesWithItem.forEach(facility -> {
//            NetworkManager.getFacilities().forEach(facility1 -> {
//                if (facility1.getCity().equals(destination)) {
//                    shortestPaths.add(GraphManager.getShortestPath(facility, facility1));
//                }
//            });
//        });
//
//        return shortestPaths;
//    }

    public float getTravelTime(int hoursDriving, int avgMph) {
//        HashMap<String, Float> travelTime = new HashMap<>();
//        items.forEach((item, integer) -> {
//            getShortestPathFromFacilities(getFacilitiesWithItem(item)).forEach(s -> {
//                travelTime.put(s, GraphManager.getTotalTime(hoursDriving, avgMph));
//            });
//        });

        return 5;
    }

    public ArrayList<Integer> getAllCosts() {
        ArrayList<Integer> costs = new ArrayList<>();

        items.forEach((item, integer) -> costs.add((item.getPrice() * integer)));
        return costs;
    }

    public int getTotalCost(ArrayList<Integer> allCosts) {
        int totalCost = 0;
        for (Integer allCost : allCosts) {
            totalCost += allCost;
        }
        return totalCost;
    }
}
