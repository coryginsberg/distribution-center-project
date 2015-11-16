package com.coryginsberg.factories;

import com.coryginsberg.Order;
import com.coryginsberg.managers.OrderManager;

import java.util.HashMap;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class OrderFactory {

    public static Order newOrder(int time, String id, String destination, String priority, HashMap<Integer, String> items) {
        Order newOrder = new Order(time, id, destination, priority, items);
        OrderManager.addOrder(newOrder);
        return newOrder;
    }
}
