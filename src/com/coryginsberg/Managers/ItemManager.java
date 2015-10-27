package com.coryginsberg.Managers;

import com.coryginsberg.Item;

import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 10/25/2015.
 * Created for Object Oriented Programming.
 */
public class ItemManager {
    public static ItemManager itemManager = new ItemManager();

    static ArrayList<Item> items = new ArrayList<>();

    public void addItem(String id, int price) {
        Item item = new Item(id, price);
        items.add(item);
    }

    public int getNumItems() {
        return items.size();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItemAtIndex(int index) {
        return items.get(index);
    }


}