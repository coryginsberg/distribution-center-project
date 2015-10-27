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


    public GenerateFacilityStatusOutput() {
        File itemsFile = new File("src/com/coryginsberg/Items.xml");
        File inventoryFile = new File("src/com/coryginsberg/FacilityInventory.xml");
        File facilityFile = new File("src/com/coryginsberg/FacilityNetwork.xml");

        new ImportFacilityNetwork().importNetwork(facilityFile);
        new ImportFacilityInventory().importInventory(inventoryFile);
        new ImportItems().importItems(itemsFile);

        graphManager.createGraph();
        graphManager.getShortestPath("Chicago, IL", "Miami, FL");

        System.out.println("Please enter City:");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        facilityManager.facilities().forEach(facility -> {
            if (facility.getCity().equals(s)) {
                printStatusOutputForCity(facility);
            }
        });

    }

    public void printStatusOutputForCity(Facility facility) {
        System.out.println();
        System.out.println();
        System.out.println(facility.getCity().toUpperCase());
        System.out.println("Direct Links: " + facility.getConnectingCities());
        System.out.println();
        System.out.println("Active Inventory");

        ArrayList<Item> depletedItems = new ArrayList<>();
        for (Item item : itemManager.getItems()) {
            depletedItems.add(item);
        }
        inventoryManager.inventories().forEach(currentInventory -> {
            if (currentInventory.getCity().equals(facility.getCity())) {
                System.out.format("%-15s%-5s", "Item ID: ", "Quantity: ");
                System.out.println();
                System.out.format("%-15s%-5s", "-------- ", "--------- ");
                System.out.println();
                for (HashMap<Integer, String> inventory : currentInventory.getInventory()) {
                    System.out.format("%-15s%-5s", inventory.values(), inventory.keySet());
                    System.out.println();
                    for (Item item : itemManager.getItems()) {
                        if (item.getId().equals(inventory.keySet().toString())) {
                            depletedItems.remove(item);
                        }
                    }
                }
            }
        });
        System.out.println();
        System.out.println("Depleted Items: " + depletedItems.toString());
    }
}
