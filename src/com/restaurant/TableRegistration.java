package com.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TableRegistration {

    private List<Table> tableRegistry;
    Scanner input = new Scanner(System.in);

    public TableRegistration() {
        this.tableRegistry = new ArrayList<>();
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
            seatsPerTable.add(noOfSeats);
        }
    }

    public void registerTables(int numberOfTables, List<Integer> numberOfSeatsPerTable) {
        for (int tableNo = 0; tableNo < numberOfTables; tableNo++) {
            Table table = new Table(tableNo, numberOfSeatsPerTable.get(tableNo));
            tableRegistry.add(table);
        }
    }

}
