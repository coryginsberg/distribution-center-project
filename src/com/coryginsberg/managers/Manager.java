package com.coryginsberg.managers;


import java.util.ArrayList;

/**
 * Created by Cory Ginsberg on 11/14/15.
 * Created for Logistics Application.
 */
public interface Manager<T> {

    Object o = new Object();
    ArrayList<T> objects = new ArrayList<>();

    void add(T t);

    ArrayList<T> get();
}
