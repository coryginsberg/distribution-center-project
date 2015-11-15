package com.coryginsberg.factories;

import com.coryginsberg.Item;
import com.coryginsberg.Order;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public class OrderFactory {

    public static Order newOrder(int time, String id, String destination, String priority, ArrayList<Item> items) {
        return new Order(time, id, destination, priority, items);
    }
}
