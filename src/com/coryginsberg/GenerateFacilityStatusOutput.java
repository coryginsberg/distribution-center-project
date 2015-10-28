package com.coryginsberg;

import com.coryginsberg.Graph.GraphManager;
import com.coryginsberg.ImportXML.ImportFacilityInventory;
import com.coryginsberg.ImportXML.ImportFacilityNetwork;
import com.coryginsberg.ImportXML.ImportItems;
import com.coryginsberg.Managers.FacilityManager;
import com.coryginsberg.Managers.InventoryManager;
import com.coryginsberg.Managers.ItemManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Cory Ginsberg on 10/27/2015.
 * Created for Logistics Application.
 */
public class GenerateFacilityStatusOutput {
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

        new ImportFacilityNetwork().importNetwork(facilityFile);
        new ImportFacilityInventory().importInventory(inventoryFile);
        new ImportItems().importItems(itemsFile);

        graphManager.createGraph();

        System.out.println("Please enter City:");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        System.out.println("Please enter driving hours per day:");
        scan = new Scanner(System.in);
        hoursDriving = scan.nextFloat();
        System.out.println("Please enter average miles per hour:");
        scan = new Scanner(System.in);
        avgMph = scan.nextFloat();

        facilityManager.facilities().forEach(facility -> {
            if (facility.getCity().equals(s)) {
                printStatusOutputForCity(facility);
            }
        });

    }

    public void printStatusOutputForCity(Facility facility) {
        System.out.println("============================================================================================");
        System.out.println(facility.getCity().toUpperCase());
        System.out.println("Direct Links: " + facility.getConnectingCitiesToString());
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
        graphManager.getShortestPath("Seattle, WA", "Nashville, TN");
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();
        graphManager.getShortestPath("New York City, NY", "Phoenix, AZ");
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();
        graphManager.getShortestPath("Fargo, ND", "Austin, TX");
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();
        graphManager.getShortestPath("Denver, CO", "Miami, FL");
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();
        graphManager.getShortestPath("Chicago, IL", "Denver, CO");
        System.out.println(graphManager.getTotalTime(hoursDriving, avgMph) + " Days");
        System.out.println();

    }

    private String stringRemoveBrackets(String str) {
        return str.substring(1, str.length() - 1);
    }
}
