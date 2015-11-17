package com.coryginsberg;

import com.coryginsberg.factories.GraphFactory;
import com.coryginsberg.graph.Graph;
import com.coryginsberg.managers.NetworkManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class ProcessOrder {
    Graph graph = GraphFactory.createGraph();
    private int time;
    private String id;
    private String destination;
    private String priority;
    private HashMap<Item, Integer> items;

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

        NetworkManager.getFacilities().forEach((facility) -> {
            if (facility.hasItem(item)) facilitiesWithItem.add(facility);
        });

        return facilitiesWithItem;
    }

}
