package com.coryginsberg.factories;

import com.coryginsberg.Item;
import com.coryginsberg.managers.ItemManager;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class ItemFactory {

    public static Item addItem(String id, int price) {
        Item newItem = new Item(id, price);
        ItemManager.addItem(newItem);
        return new Item(id, price);
    }

}
