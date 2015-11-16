package com.coryginsberg;

import com.coryginsberg.factories.GraphFactory;
import com.coryginsberg.graph.Graph;
import com.coryginsberg.managers.GraphManager;

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
    private HashMap<Integer, String> items;

    public ProcessOrder(Order order) {
        this.time = order.getTime();
        this.id = order.getID();
        this.destination = order.getDestination();
        this.priority = order.getPriority();
        this.items = order.getItems();
        System.out.println(graph.toString());
//        items.forEach((amt, id) -> {
//            HashMap<Integer, String> item = new HashMap<>();
//            item.put(amt, id);
//            facilitiesWithItem(item);
//            System.out.println(item.toString());
//        });
        facilitiesWithItem(items);
    }

    private ArrayList<Network> facilitiesWithItem(HashMap<Integer, String> item) {
        ArrayList<Facility> facilitiesWithItem = new ArrayList<>();

        GraphManager.getAllFacilities();

        return null;
    }


}
