package com.coryginsberg;

import com.coryginsberg.importxml.*;
import com.coryginsberg.managers.FacilityManager;
import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;

import java.nio.file.FileAlreadyExistsException;

/**
 * Outputs Each Facility's initial status and prints it out to the terminal.
 *
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

        hoursDriving = 8;
        avgMph = 50;
        System.out.println("All times are calculated for driving an average of 8 hours a day at 50 MPH");
        System.out.println();

        // Print the status of all facilities.
        FacilityManager.getFacilities().forEach(this::printStatusOutputForCity);
    }

    public void printStatusOutputForCity(Network network) {
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

        InventoryManager.inventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(network.getCity())) {
                System.out.format("%-15s%-5s", "Item ID:", "Quantity:");
                System.out.println();
                System.out.format("%-15s%-5s", "‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾");
                System.out.println();
                // TODO: 11/8/15 Get the 'Active Inventory' to print properly.
                currentInventory.getNondepletedInventory().forEach((s, integer) -> {
                    System.out.format("%-15s%-5s", s, integer);
                    System.out.println();
                });
            }
        });

        System.out.println();
        System.out.println("DEPLETED ITEMS: ");
        System.out.println();
        System.out.println("SCHEDULE:");
        System.out.println(); // TODO: Add the Schedule for each Facility
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
    }
}
