package com.restaurant;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SeatAvailabilityChecker {

    private static final boolean AVAILABLE = true;
    private static final boolean UNAVAILABLE = false;
    private Scanner input = new Scanner(System.in);

    public TableAvailability checkTableAvailability(List<Table> tableList, int totalAvailableSeats) {
        InputHelper inputHelper = new InputHelper();
        while (true) {
            int requiredNoOfSeats = 0;
            String tableTypeChoice = "";
            System.out.print("\nenter the number of seats required : ");
            requiredNoOfSeats = inputHelper.readInt();
            if (requiredNoOfSeats <= totalAvailableSeats) {
                while (true) {
                    System.out.print("\nshould they be on the same table? (Y/y or N/n) : ");
                    tableTypeChoice = input.next();
                    if (tableTypeChoice.matches("^[Yy]$")) {
                        return this.availabilityCheckForSameTableSeats(tableList, requiredNoOfSeats);
                    } else if (tableTypeChoice.matches("^[Nn]$")) {
                        return this.availabilityCheckForDifferentTableSeats(tableList, totalAvailableSeats, requiredNoOfSeats);
                    } else {
                        System.out.println("\nincorrect choice!! please try again..");
                    }
                }
            }
            return new TableAvailability(UNAVAILABLE);
        }
    }

    private TableAvailability availabilityCheckForDifferentTableSeats(List<Table> tableRegistry, int totalAvailableSeats, int requiredNoOfSeats) {
        List<Table> availableTables = new ArrayList<>();
        int seats = 0;
        for (Table table : tableRegistry) {
            if (table.getAvailableSeats() > 0) {
                availableTables.add(table);
                seats += table.getAvailableSeats();
                if (seats >= requiredNoOfSeats) {
                    int remainingSeatsFromLastTable = seats - requiredNoOfSeats;
                    this.updateTableDetails(availableTables, remainingSeatsFromLastTable);
                    return new TableAvailability(availableTables, AVAILABLE);
                }
            }
        }
        return new TableAvailability(UNAVAILABLE);
    }

    private TableAvailability availabilityCheckForSameTableSeats(List<Table> tableRegistry, int requiredNoOfSeats) {
        List<Table> availableTables = new ArrayList<>();
        for (Table table : tableRegistry) {
            if (table.getAvailableSeats() >= requiredNoOfSeats) {
                availableTables.add(table);
                int remainingNoOfSeats = table.getNumberOfSeats() - requiredNoOfSeats;
                this.updateTableDetails(availableTables, remainingNoOfSeats);
                return new TableAvailability(availableTables, AVAILABLE);
            }
        }
        return new TableAvailability(UNAVAILABLE);
    }

    private void updateTableDetails(List<Table> availableTables, int remainingSeatsFromLastTable) {
        for (int i = 0; i < availableTables.size() - 1; i++) {
            Table table = availableTables.get(0);
            table.setAvailableSeats(0);
        }
        Table lastTable = availableTables.get(availableTables.size() - 1);
        lastTable.setAvailableSeats(remainingSeatsFromLastTable);
    }

}
