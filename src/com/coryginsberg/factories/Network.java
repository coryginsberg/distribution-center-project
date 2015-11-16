package com.coryginsberg.factories;

/**
 * Created by Cory Ginsberg on 11/16/15.
 * Created for Logistics Application.
 */
public class Network {
    private static Network ourInstance = new Network();

    private Network() {
    }

    public static Network getInstance() {
        return ourInstance;
    }
}
