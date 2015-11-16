package com.coryginsberg.output;

import com.coryginsberg.Order;
import com.coryginsberg.importxml.ImportOrderFile;
import com.coryginsberg.importxml.UnexpectedNodeException;
import com.coryginsberg.managers.OrderManager;

import java.nio.file.FileAlreadyExistsException;

/**
 * @author Cory Ginsberg
 * @version 1.0
 * @since 11/5/2015
 */
public class GenerateOrderOutput implements OutputInterface<Order> {
    private static ImportOrderFile importOrderFile = new ImportOrderFile();
    private static int orderNum = 0;
    public GenerateOrderOutput() throws FileAlreadyExistsException, UnexpectedNodeException {
        importOrderFile.importFile("src/com/coryginsberg/xml/Orders.xml");

        // These extra println's are here to give a break between the Facility Status Output and the Order Output.
        // Since the Order Output comes after the Facility Status Output, it is placed here.
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(" ---------------- ");
        System.out.println("| ORDER OUTPUTS: |");
        System.out.println(" ---------------- ");
        OrderManager.getOrders().forEach(this::printStatusOutput);
    }

    public void printStatusOutput(Order order) {
        orderNum++;
        // Place all outputs for the order between these two lines.
        System.out.println("============================================================================================");
        System.out.println("| ORDER #" + orderNum);
        System.out.println("| ‾‾‾‾‾‾‾‾");
        System.out.println("| * Order ID: " + order.getID());
        System.out.println("| * Order Time: " + order.getTime());
        System.out.println("| * Destination: " + order.getDestination());
        System.out.println("| * Priority: " + order.getPriority());
        System.out.println("| * List of Ordered Items: ");
        System.out.format("%-5s%-15s%-5s", "| ", "Item ID:", "Amount:");
        System.out.println();
        System.out.format("%-5s%-15s%-5s", "| ", "‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾");
        System.out.println();
        order.getItems().forEach((amt, id) -> {
            System.out.format("%-5s%-15s%-5s", "| ", id, amt);
            System.out.println();

        });
        System.out.println("|-------------------------------------------------------------------------------------------");
        System.out.println("| ");
        System.out.println("| PROCESSING SOLUTION:");
        System.out.println("| ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾");
        System.out.println("| * Destination: " + order.getDestination());
        System.out.println("| * Priority: " + order.getPriority());
        System.out.println("| * Total Cost: "); // TODO: 11/15/15 Get the Total Cost of the order.
        System.out.println("| * First Delivery Day: "); // TODO: 11/15/15 Get the day that the first shipment comes into the facility.
        System.out.println("| * Last Delivery Day: "); // TODO: 11/15/15 Get the day that the last shipment comes into the facility.
        System.out.println("| * Ordered Items: ");
        System.out.format("%-5s%-15s%-15s%-10s%-15s%-15s%-15s", "| ", "Item ID:", "Quantity:", "Cost", "Num. Sources", "First Day", "Last Day");
        System.out.println();
        System.out.format("%-5s%-15s%-15s%-10s%-15s%-15s%-15s", "| ", "‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾", "‾‾‾‾", "‾‾‾‾‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾‾", "‾‾‾‾‾‾‾‾");
        System.out.println();
        order.getItems().forEach((amt, id) -> { // TODO: 11/15/15 Switch this out with the properly calculated one.
            System.out.format("%-5s%-15s%-15s%-10s%-15s%-15s%-15s", "| ", id, amt, "", "", "", "");
            System.out.println();

        });
        System.out.println("============================================================================================");
        System.out.println();

    }

}
