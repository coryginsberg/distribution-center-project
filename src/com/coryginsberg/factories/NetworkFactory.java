package com.coryginsberg.factories;

import com.coryginsberg.Network;
import com.coryginsberg.managers.NetworkManager;

import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/4/2015
 */
public class NetworkFactory {

    private NetworkFactory() {
    }

    public static Network addFacility(String name, int rate, int cost, HashMap<Integer, String> linkedCities) {
        Network newNetwork = new Network(name, rate, cost, linkedCities);
        NetworkManager.addFacility(newNetwork);
        return newNetwork;
    }
}

