package com.coryginsberg;

import com.coryginsberg.importxml.*;
import com.coryginsberg.managers.FacilityManager;
import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;
import com.coryginsberg.managers.ItemManager;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class GenerateFacilityStatusOutput implements OutputInterface {
    private static Import importNetworkFile = new ImportNetworkFile();
    private static Import importItemFile = new ImportItemFile();
    private static Import importInventoryFile = new ImportInventoryFile();

    private static GraphManager graphManager = new GraphManager();

    private static float hoursDriving;
    private static float avgMph;

    public GenerateFacilityStatusOutput() throws FileAlreadyExistsException, UnexpectedNodeException {
        importNetworkFile.importFile("src/com/coryginsberg/FacilityNetwork.xml");
        importInventoryFile.importFile("src/com/coryginsberg/FacilityInventory.xml");
        importItemFile.importFile("src/com/coryginsberg/Items.xml");
        GraphManager.createGraph();

        String s = "Chicago, IL";
        hoursDriving = 8;
        avgMph = 50;
        System.out.println("All times are calculated for driving 8 hours a day at 50 MPH");
        System.out.println();
        FacilityManager.facilities().forEach(facility -> {
            if (facility.getCity().equals(s)) {
                printStatusOutputForCity(facility);
            }
        });
    }

    public void printStatusOutputForCity(Network network) {
        System.out.println("============================================================================================");
        System.out.println(network.getCity().toUpperCase());
        System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾");

        System.out.print("DIRECT LINKS: ");
        for (HashMap<Integer, String> connectedFacility : network.getConnectingCities()) {
            System.out.print(connectedFacility.values().toString().substring(1, connectedFacility.values().toString().length() - 1));
            graphManager.getShortestPath(network.getCity(), connectedFacility.values().toString().substring(1, connectedFacility.values().toString().length() - 1));
            System.out.print(" (" + graphManager.getTotalTime(hoursDriving, avgMph) + " Days) ");
        }
        System.out.println();
        System.out.println();
        System.out.println("ACTIVE INVENTORY:");

        ArrayList<Item> depletedItems = new ArrayList<>();
        ItemManager.getItems().forEach(depletedItems::add);

        InventoryManager.inventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(network.getCity())) {
                System.out.format("%-15s%-5s", "Item ID:", "Quantity:");
                System.out.println();
                System.out.format("%-15s%-5s", "‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾");
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
        System.out.println("DEPLETED ITEMS: " + stringRemoveBrackets(depletedItems.toString()));
        System.out.println();
        System.out.println("SCHEDULE:");
        System.out.println(); // TODO: Add the Schedule for each Facility
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
    }
}
