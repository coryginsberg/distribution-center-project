package com.coryginsberg;

import com.coryginsberg.importxml.*;
import com.coryginsberg.managers.FacilityManager;
import com.coryginsberg.managers.GraphManager;
import com.coryginsberg.managers.InventoryManager;

import java.nio.file.FileAlreadyExistsException;
import java.util.*;
import java.util.Arrays;
import java.util.List;

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
                System.out.format("%-15s%-5s", "‾‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾‾");
                System.out.println();
                // TODO: 11/8/15 Get the 'Active Inventory' to print properly.
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
        System.out.println();
        System.out.println("SCHEDULE:");
        System.out.println(); // TODO: Add the Schedule for each Facility
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
    }
}

class Columns {

    List<List<String>> lines = new ArrayList<>();
    List<Integer> maxLengths = new ArrayList<>();
    int numColumns = -1;

    public Columns addLine(String... line) {

        if (numColumns == -1){
            numColumns = line.length;
            for(int i = 0; i < numColumns; i++) {
                maxLengths.add(0);
            }
        }

        if (numColumns != line.length) {
            throw new IllegalArgumentException();
        }

        for(int i = 0; i < numColumns; i++) {
            maxLengths.set(  i, Math.max( maxLengths.get(i), line[i].length() )  );
        }

        lines.add( Arrays.asList(line) );

        return this;
    }

    public void print(){
        System.out.println( toString() );
    }

    public String toString(){
        String result = "";
        for(List<String> line : lines) {
            for(int i = 0; i < numColumns; i++) {
                result += pad( line.get(i), maxLengths.get(i) + 1 );
            }
            result += System.lineSeparator();
        }
        return result;
    }

    private String pad(String word, int newLength){
        while (word.length() < newLength) {
            word += " ";
        }
        return word;
    }
}
