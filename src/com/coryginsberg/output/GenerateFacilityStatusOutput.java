package com.coryginsberg.output;

import com.coryginsberg.Network;
import com.coryginsberg.importxml.*;
import com.coryginsberg.managers.FacilityManager;
import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

/**
 * Outputs Each Facility's initial status and prints it out to the terminal.
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class GenerateFacilityStatusOutput implements OutputInterface<Network> {
    private static Import importNetworkFile = new ImportNetworkFile();
    private static Import importItemFile = new ImportItemFile();
    private static Import importInventoryFile = new ImportInventoryFile();

    private static GraphManager graphManager = new GraphManager();

    private static float hoursDriving;
    private static float avgMph;

    public GenerateFacilityStatusOutput() throws FileAlreadyExistsException, UnexpectedNodeException {
        importNetworkFile.importFile("src/com/coryginsberg/xml/FacilityNetwork.xml");
        importInventoryFile.importFile("src/com/coryginsberg/xml/FacilityInventory.xml");
        importItemFile.importFile("src/com/coryginsberg/xml/Items.xml");
        GraphManager.createGraph();

        hoursDriving = 8;
        avgMph = 50;
        System.out.println("All times are calculated for driving an average of 8 hours a day at 50 MPH");
        System.out.println();

        // Print the status of all facilities.
        FacilityManager.getFacilities().forEach(this::printStatusOutput);
    }

    public void printStatusOutput(Network network) {
        System.out.println("============================================================================================");
        System.out.println(network.getCity().toUpperCase());
        System.out.println("‾‾‾‾‾‾‾‾‾‾‾‾");

        System.out.print("DIRECT LINKS: ");
        network.getConnectingCities().forEach((integer, s) -> {
            System.out.print(s);
            graphManager.getShortestPath(network.getCity(), s);
            System.out.print(" (" + graphManager.getTotalTime(hoursDriving, avgMph) + " Days) ");
        });

        System.out.println();
        System.out.println();
        System.out.println("ACTIVE INVENTORY:");

        InventoryManager.getInventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(network.getCity())) {
                System.out.format("%-15s%-5s", "Item ID:", "Quantity:");
                System.out.println();
                System.out.format("%-15s%-5s", "‾‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾");
                System.out.println();
                currentInventory.getNondepletedInventory().forEach((s, integer) -> {

                });
                Map<String, Integer> map = currentInventory.getNondepletedInventory();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    System.out.format("%-15s%-5s", key, value);
                    System.out.println();
                }
            }
        });

        System.out.println();
        System.out.println("DEPLETED ITEMS: ");
        InventoryManager.getInventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(network.getCity())) {
                Map<String, Integer> map = currentInventory.getDepletedInventory();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    System.out.println(key);
                }
            }
        });
        System.out.println();
        System.out.println("SCHEDULE:");
        System.out.println(); // TODO: Add the Schedule for each Facility
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
    }
}
