package com.restaurant;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TableRegistration {

    private int totalAvailableSeats;
    private Scanner input;

    public TableRegistration() {
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

    public List<Table> enterTableDetails(int noOfTables) {
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
        return this.registerTables(noOfTables, seatsPerTable);
    }

    private void throwIfLessThan1(int number) {
        if (number < 1)
            throw new InputMismatchException();
    }

    private List<Table> registerTables(int numberOfTables, List<Integer> numberOfSeatsPerTable) {
        List<Table> tables = new ArrayList<>();
        for (int tableNo = 0; tableNo < numberOfTables; tableNo++) {
            Table table = new Table((tableNo + 1), numberOfSeatsPerTable.get(tableNo));
            tables.add(table);
        }
        return tables;
    }

    public int getTotalAvailableSeats() {
        return this.totalAvailableSeats;
    }
}
