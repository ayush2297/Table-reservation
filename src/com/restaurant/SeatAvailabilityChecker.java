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
        while (true) {
            int requiredNoOfSeats = 0;
            String tableTypeChoice = "";
            while (true) {
                try {
                    System.out.print("\nenter the number of seats required : ");
                    requiredNoOfSeats = input.nextInt();
                    this.throwIfLessThan1(requiredNoOfSeats);
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.println("************ Incorrect input.... please enter correct input ***********");
                    continue;
                }
                break;
            }
            while (true) {
                System.out.print("\nshould they be on the same table? (Y/y or N/n) : ");
                tableTypeChoice = input.next();
                if (tableTypeChoice.matches("^[Yy]$")) {
                    return this.availabilityCheckForSameTableSeats(tableList,requiredNoOfSeats);
                } else if (tableTypeChoice.matches("^[Nn]$")) {
                    return this.availabilityCheckForDifferentTableSeats(tableList,totalAvailableSeats,requiredNoOfSeats);
                } else {
                    System.out.println("\nincorrect choice!! please try again..");
                }
            }
        }
    }

    private TableAvailability availabilityCheckForDifferentTableSeats(List<Table> tableRegistry, int totalAvailableSeats, int requiredNoOfSeats) {
        List<Table> availableTables = new ArrayList<>();
        int seats = 0;
        if (totalAvailableSeats >= requiredNoOfSeats) {
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

    private void throwIfLessThan1(int number) {
        if (number < 1)
            throw new InputMismatchException();
    }
}
