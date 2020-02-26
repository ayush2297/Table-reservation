package com.restaurant;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TableRegistration {

    private static final boolean AVAILABLE = true;
    private static final boolean UNAVAILABLE = false;
    private int totalAvailableSeats;
    private List<Table> tableRegistry;
    private Scanner input;

    public TableRegistration() {
        this.tableRegistry = new ArrayList<>();
        this.totalAvailableSeats = 0;
        this.input = new Scanner(System.in);
    }

    public int addTableCount() {
        while (true) {
            int noOfTables = 0;
            boolean isCorrectInput = true;
            try {
                System.out.println("enter the number of tables you want to add  :");
                noOfTables = input.nextInt();
                this.throwIfLessThan1(noOfTables);
            } catch (InputMismatchException e) {
                input.nextLine();
                continue;
            }
            return noOfTables;
        }
    }

    public void enterTableDetails(int noOfTables) {
        List<Integer> seatsPerTable = new ArrayList<>();
        for (int tableNo = 0; tableNo < noOfTables; tableNo++) {
            int noOfSeats = 0;
            while (true) {
                try {
                    System.out.println("enter the number of seats available on table no. " + (tableNo + 1) + " : ");
                    noOfSeats = input.nextInt();
                    this.throwIfLessThan1(noOfSeats);
                } catch (InputMismatchException e) {
                    System.out.println("************ Incorrect input.... please enter a number ***********");
                    input.nextLine();
                    continue;
                }
                break;
            }
            this.totalAvailableSeats += noOfSeats;
            seatsPerTable.add(noOfSeats);
        }
        this.registerTables(noOfTables, seatsPerTable);
    }

    public TableAvailability checkTableAvailability() {
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
                    return this.availabilityCheckForSameTableSeats(requiredNoOfSeats);
                } else if (tableTypeChoice.matches("^[Nn]$")) {
                    return this.availabilityCheckForDifferentTableSeats(requiredNoOfSeats);
                } else {
                    System.out.println("\nincorrect choice!! please try again..");
                }
            }
        }
    }

    private void throwIfLessThan1(int number) {
        if (number < 1)
            throw new InputMismatchException();
    }

    private void registerTables(int numberOfTables, List<Integer> numberOfSeatsPerTable) {
        for (int tableNo = 0; tableNo < numberOfTables; tableNo++) {
            Table table = new Table((tableNo + 1), numberOfSeatsPerTable.get(tableNo));
            tableRegistry.add(table);
        }
    }

    private TableAvailability availabilityCheckForDifferentTableSeats(int requiredNoOfSeats) {
        List<Table> availableTables = new ArrayList<>();
        int seats = 0;
        if (totalAvailableSeats >= requiredNoOfSeats) {
            for (Table table : this.tableRegistry) {
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

    private TableAvailability availabilityCheckForSameTableSeats(int requiredNoOfSeats) {
        List<Table> availableTables = new ArrayList<>();
        for (Table table : this.tableRegistry) {
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
