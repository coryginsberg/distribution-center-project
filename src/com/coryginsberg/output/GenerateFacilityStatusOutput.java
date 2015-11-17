package com.coryginsberg.output;

import com.coryginsberg.Facility;
import com.coryginsberg.importxml.ImportFiles;
import com.coryginsberg.importxml.UnexpectedNodeException;
import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;
import com.coryginsberg.managers.NetworkManager;

import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

/**
 * Outputs Each Facility's initial status and prints it out to the terminal.
 *
 * @author Cory Ginsberg
 * @version 1.0
 * @since 10/25/2015
 */

public class GenerateFacilityStatusOutput implements OutputInterface<Facility> {

    private static float hoursDriving;
    private static float avgMph;

    public GenerateFacilityStatusOutput() throws FileAlreadyExistsException, UnexpectedNodeException {

        ImportFiles.getInstance();

        hoursDriving = 8;
        avgMph = 50;
        System.out.println(" _____________________________________________________________________________________");
        System.out.println("| *** All times are calculated for driving an average of 8 hours a day at 50 MPH. *** |");
        System.out.println(" ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        System.out.println();
        System.out.println(" --------------------------");
        System.out.println("| FACILITY STATUS OUTPUTS: |");
        System.out.println(" --------------------------");
        // Print the status of all facilities.
        NetworkManager.getFacilities().forEach(this::printStatusOutput);
    }

    @Override
    public void printStatusOutput(Facility facility) {
        System.out.println("============================================================================================");
        System.out.println("| " + facility.getCity().toUpperCase());
        System.out.println("| ‾‾‾‾‾‾‾‾‾‾‾‾");

        System.out.print("| DIRECT LINKS: ");
        facility.getConnectingCities().forEach((s, integer) -> {
            System.out.print(s.getCity());
            GraphManager.getShortestPath(facility, s);
            System.out.print(" (" + GraphManager.getTotalTime(hoursDriving, avgMph) + " Days) ");
        });

        System.out.println();
        System.out.println("| ");
        System.out.println("| ACTIVE INVENTORY:");

        InventoryManager.getInventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(facility.getCity())) {
                System.out.format("%-5s%-15s%-5s", "| ", "Item ID:", "Quantity:");
                System.out.println();
                System.out.format("%-5s%-15s%-5s", "| ", "‾‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾");
                System.out.println();
                currentInventory.getNondepletedInventory().forEach((s, integer) -> {

                });
                Map<String, Integer> map = currentInventory.getNondepletedInventory();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    System.out.format("%-5s%-15s%-5s", "| ", key, value);
                    System.out.println();
                }
            }
        });

        System.out.println("| ");
        System.out.println("| DEPLETED ITEMS: ");
        InventoryManager.getInventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(facility.getCity())) {
                Map<String, Integer> map = currentInventory.getDepletedInventory();
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    String key = entry.getKey();
                    System.out.println(key);
                }
            }
        });
        System.out.println("| ");
        System.out.println("| SCHEDULE:");
        System.out.print("| ");
        System.out.format("%15s", "Day: ");
        for (int i = 0; i < facility.getDailyRate().size(); i++) {
            System.out.format("%-5s", i);
        }
        System.out.format("%-5s", "...");
        System.out.println();
        System.out.print("| ");
        System.out.format("%15s", "Available: ");
        for (int i = 0; i < facility.getDailyRate().size(); i++) {
            System.out.format("%-5s", facility.getRate());
        }
        System.out.format("%-5s", "...");
        System.out.println();
        System.out.println("| ");
        System.out.println("| ");
        System.out.println("============================================================================================");
        System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
    }
}
