package com.coryginsberg;

import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;
import com.coryginsberg.managers.NetworkManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class ProcessOrder {
    private int time;
    private String destination;
    private HashMap<Item, Integer> items = new HashMap<>();

    public ProcessOrder(Order order) {
        this.time = order.getTime();
        this.destination = order.getDestination();
        this.items = order.getItems();
        items.forEach((item, amt) -> getFacilitiesWithItem(item));
    }

    private Facility getDestinationFacility() {
        for (Facility facility : NetworkManager.getFacilities()) {
            if (facility.getCity().equals(destination)) {
                return facility;
            }
        }
        return null;
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

    public float getTravelTimeForItem(int hoursDriving, int avgMph, Item item) {

        GraphManager.getShortestPath(getFacilitiesWithItem(item).get(0), getDestinationFacility());
        return GraphManager.getTotalTime(hoursDriving, avgMph);
    }

    private int getCost(Item item) {
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (entry.getKey().equals(item)) {
                return item.getPrice();
            }
        }
        return 0;
    }

    public int getTotalCostOfItem(Item item) {
        int totalCost = getCost(item);
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            if (item.equals(entry.getKey())) {
                totalCost *= entry.getValue();
            }
        }

        return totalCost;
    }

    public int getFirstDeliveryDate() {
        int dayLeavingFacility = 0;

        float travelTime;
        ArrayList<Float> allTravelTimes = new ArrayList<>();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            travelTime = getTravelTimeForItem(8, 50, itemEntry.getKey());
            allTravelTimes.add(travelTime);
        }
        travelTime = Collections.min(allTravelTimes);

        return time + dayLeavingFacility + (int) travelTime;
    }

    public int getLastDeliveryDate() {
        int dayLeavingFacility = 0;

        float travelTime;
        ArrayList<Float> allTravelTimes = new ArrayList<>();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            travelTime = getTravelTimeForItem(8, 50, itemEntry.getKey());
            allTravelTimes.add(travelTime);
        }
        travelTime = Collections.max(allTravelTimes);

        return time + dayLeavingFacility + (int) travelTime;
    }

    public int getFirstDayForItem(Item item) {

        float travelTime;
        ArrayList<Float> allTravelTimes = new ArrayList<>();
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            if (itemEntry.getKey().equals(item)) {
                travelTime = getTravelTimeForItem(8, 50, itemEntry.getKey());
                allTravelTimes.add(travelTime);
            }
        }
        travelTime = Collections.min(allTravelTimes);

        return time + getDayLeavingFacility(item) + (int) travelTime;
    }

    public int getDayLeavingFacility(Item item) {
        int dayLeavingFacility = 0;

        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            for (Facility facility : getFacilitiesWithItem(item)) {
                float facilityRate = facility.getRate();
                if (facilityRate < itemEntry.getValue()) {
                    facilityRate += facilityRate;
                }
                dayLeavingFacility = (int) facilityRate / facility.getRate();
            }
        }
        return dayLeavingFacility;
    }

    public int getLastDayForItem(Item item) {


        float travelTime;
        ArrayList<Float> allTravelTimes = new ArrayList<>();
        ArrayList<Integer> allLastDays = new ArrayList<>();
        int lastDayLeaving = getDayLeavingFacility(item);
        for (Map.Entry<Item, Integer> itemEntry : items.entrySet()) {
            if (itemEntry.getKey().equals(item)) {
                travelTime = getTravelTimeForItem(8, 50, itemEntry.getKey());
                allTravelTimes.add(travelTime);
                allLastDays.add(lastDayLeaving);
            }
        }
        travelTime = Collections.max(allTravelTimes);
        lastDayLeaving = Collections.max(allLastDays);

        return time + lastDayLeaving + (int) travelTime;
    }
}
