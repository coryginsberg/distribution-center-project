package com.coryginsberg.factories;

import com.coryginsberg.Item;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class ItemFactory {

    public static Item addItem(String id, int price) {
        return new Item(id, price);
    }

}
