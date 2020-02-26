package com.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableRegistration {

    private int totalAvailableSeats;
    private List<Table> tableRegistry;
    Scanner input = new Scanner(System.in);

    public TableRegistration() {
        this.tableRegistry = new ArrayList<>();
        this.totalAvailableSeats = 0;
    }

    public int addTableCount() {
        System.out.println("enter the number of tables you want to add  :");
        return input.nextInt();
    }

    public void enterTableDetails(int noOfTables) {
        List<Integer> seatsPerTable = new ArrayList<>();
        for (int tableNo = 0; tableNo < noOfTables; tableNo++) {
            System.out.println("enter the number of seats available on table no. " + (tableNo + 1) + " : ");
            int noOfSeats = input.nextInt();
            this.totalAvailableSeats += noOfSeats;
            seatsPerTable.add(noOfSeats);
        }
        this.registerTables(noOfTables, seatsPerTable);
    }

    public void registerTables(int numberOfTables, List<Integer> numberOfSeatsPerTable) {
        for (int tableNo = 0; tableNo < numberOfTables; tableNo++) {
            Table table = new Table((tableNo + 1), numberOfSeatsPerTable.get(tableNo));
            tableRegistry.add(table);
        }
    }

    public void showTables() {
        this.tableRegistry.forEach(table -> System.out.println(table.toString()));
    }

    public boolean checkTableAvailability() {
        while (true) {
            System.out.print("\nenter the number of seats required : ");
            int requiredNoOfSeats = input.nextInt();
            System.out.print("\nshould they be on the same table? (Y/y or N/n) : ");
            String tableTypeChoice = input.next();
            if (tableTypeChoice.matches("^[Yy]$")) {
                return this.availabilityCheckForSameTableSeats(requiredNoOfSeats);
            } else if (tableTypeChoice.matches("^[Nn]$")) {
                return this.availabilityCheckForDifferentTableSeats(requiredNoOfSeats);
            } else {
                System.out.println("\nincorrect choice!! please try again..");
            }
        }
    }

    private boolean availabilityCheckForDifferentTableSeats(int requiredNoOfSeats) {
        if (totalAvailableSeats >= requiredNoOfSeats) {
            return true;
        }
        return false;
    }

    private boolean availabilityCheckForSameTableSeats(int requiredNoOfSeats) {
        long noOfTablesAvailForThisReq = this.tableRegistry.stream().filter(table -> table.getNumberOfSeats() >= requiredNoOfSeats).count();
        if (noOfTablesAvailForThisReq > 0) {
            return true;
        }
        return false;
    }
}
