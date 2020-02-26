package com.restaurant;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableRegistration {

    private InputHelper inputHelper;
    private int totalAvailableSeats;
    private Scanner input;

    public TableRegistration() {
        this.inputHelper = new InputHelper();
        this.totalAvailableSeats = 0;
        this.input = new Scanner(System.in);
    }

    public int addTableCount() {
        while (true) {
            int noOfTables = 0;
            boolean isCorrectInput = true;
            System.out.println("enter the number of tables you want to add  :");
            noOfTables = inputHelper.readInt();
            return noOfTables;
        }
    }

    public List<Table> enterTableDetails(int noOfTables) {
        List<Integer> seatsPerTable = new ArrayList<>();
        for (int tableNo = 0; tableNo < noOfTables; tableNo++) {
            System.out.println("enter the number of seats available on table no. " + (tableNo + 1) + " : ");
            int noOfSeats = inputHelper.readInt();
            this.totalAvailableSeats += noOfSeats;
            seatsPerTable.add(noOfSeats);
        }
        return this.registerTables(noOfTables, seatsPerTable);
    }

    private List<Table> registerTables(int numberOfTables, List<Integer> numberOfSeatsPerTable) {
        List<Table> tables = IntStream.range(0, numberOfTables).mapToObj(tableNo -> new Table((tableNo + 1), numberOfSeatsPerTable.get(tableNo))).collect(Collectors.toList());
        return tables;
    }

    public int getTotalAvailableSeats() {
        return this.totalAvailableSeats;
    }
}
