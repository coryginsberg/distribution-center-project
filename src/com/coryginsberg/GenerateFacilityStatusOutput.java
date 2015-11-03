package com.coryginsberg;

import com.coryginsberg.importxml.ImportFacilityInventory;
import com.coryginsberg.importxml.ImportFacilityNetwork;
import com.coryginsberg.importxml.ImportFile;
import com.coryginsberg.importxml.ImportItems;
import com.coryginsberg.managers.FacilityManager;
import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;
import com.coryginsberg.managers.ItemManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class GenerateFacilityStatusOutput implements OutputInterface {
    private static ImportFile importFile = new ImportFile();
    private static FacilityManager facilityManager = new FacilityManager();
    private static InventoryManager inventoryManager = new InventoryManager();
    private static ItemManager itemManager = new ItemManager();
    private static GraphManager graphManager = new GraphManager();

    private static float hoursDriving;
    private static float avgMph;

    public GenerateFacilityStatusOutput() {
        File itemsFile = new File("src/com/coryginsberg/Items.xml");
        File inventoryFile = new File("src/com/coryginsberg/FacilityInventory.xml");
        File facilityFile = new File("src/com/coryginsberg/FacilityNetwork.xml");
        //importFile.importFile("src/com/coryginsberg/Items.xml");
        //importFile.importFile("src/com/coryginsberg/FacilityInventory.xml");
        importFile.importFile("src/com/coryginsberg/FacilityNetwork.xml");

        new ImportFacilityNetwork().importFile(facilityFile);
        new ImportFacilityInventory().importFile(inventoryFile);
        new ImportItems().importFile(itemsFile);

        graphManager.createGraph();

        String s = "Chicago, IL";
        hoursDriving = 8;
        avgMph = 50;

        System.out.println("All times are calculated for driving 8 hours a day at 50 MPH");

        facilityManager.facilities().forEach(facility -> {
            if (facility.getCity().equals(s)) {
                printStatusOutputForCity(facility);
            }
        });

    }

    public void printStatusOutputForCity(Facility facility) {
        System.out.println("============================================================================================");
        System.out.println(facility.getCity().toUpperCase());
        System.out.print("Direct Links: ");
        for (HashMap<Float, String> connectedFacility : facility.getConnectingCities()) {
            System.out.print(connectedFacility.values().toString().substring(1, connectedFacility.values().toString().length() - 1));
            graphManager.getShortestPath(facility.getCity(), connectedFacility.values().toString().substring(1, connectedFacility.values().toString().length() - 1));
            System.out.print(" (" + graphManager.getTotalTime(hoursDriving, avgMph) + " Days) ");
        }
        System.out.println();
        System.out.println();
        System.out.println("Active Inventory");

        ArrayList<Item> depletedItems = new ArrayList<>();
        itemManager.getItems().forEach(depletedItems::add);

        inventoryManager.inventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(facility.getCity())) {
                System.out.format("%-15s%-5s", "Item ID: ", "Quantity: ");
                System.out.println();
                System.out.format("%-15s%-5s", "-------- ", "--------- ");
                System.out.println();
                for (HashMap<Integer, String> inventory : currentInventory.getInventory()) {
                    System.out.format("%-15s%-5s", stringRemoveBrackets(inventory.values().toString()), stringRemoveBrackets(inventory.keySet().toString()));
                    System.out.println();

                    for (String value : inventory.values()) {
                        for (int i = 0; i < depletedItems.size(); i++) {
                            if (value.equals(depletedItems.get(i).getId())) {
                                depletedItems.remove(i);
                            }
                        }
                    }
                }
            }
        });

        System.out.println();
        System.out.println("Depleted Items: " + stringRemoveBrackets(depletedItems.toString()));
        System.out.println("============================================================================================");
        System.out.println();
        System.out.println("Shortest Path results: ");
        System.out.println("----------------------");

        String startCity = "Seattle, WA";
        String endCity = "Nashville, TN";
        System.out.println("City Start: " + startCity + " -> City End: " + endCity);
        System.out.println(graphManager.getShortestPath(startCity, endCity));
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();

        startCity = "New York City, NY";
        endCity = "Phoenix, AZ";
        System.out.println("City Start: " + startCity + " -> City End: " + endCity);
        System.out.println(graphManager.getShortestPath(startCity, endCity));
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();

        startCity = "Fargo, ND";
        endCity = "Austin, TX";
        System.out.println("City Start: " + startCity + " -> City End: " + endCity);
        System.out.println(graphManager.getShortestPath(startCity, endCity));
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();

        startCity = "Denver, CO";
        endCity = "Miami, FL";
        System.out.println("City Start: " + startCity + " -> City End: " + endCity);
        System.out.println(graphManager.getShortestPath(startCity, endCity));
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();

    }
}
