package com.coryginsberg.managers;

import com.coryginsberg.Order;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class OrderManager {

    private static ArrayList<Order> orders = new ArrayList<>();

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static ArrayList<Order> get() {
        return orders;
    }
}
