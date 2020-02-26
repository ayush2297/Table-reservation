package com.restaurant;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RestaurantManagerMenu {
    private List<Table> tableList;
    private TableRegistration tableRegistration;
    private SeatAvailabilityChecker seatAvailabilityChecker;

    public RestaurantManagerMenu() {
        this.tableList = new ArrayList<>();
        this.tableRegistration = new TableRegistration();
        this.seatAvailabilityChecker = new SeatAvailabilityChecker();
    }

    public void displayMenu() {
        Scanner input= new Scanner(System.in);
        int noOfTables = this.tableRegistration.addTableCount();
        this.tableList = this.tableRegistration.enterTableDetails(noOfTables);
        int totalAvailableSeats = this.tableRegistration.getTotalAvailableSeats();
        boolean stopApp = false;
        while (true) {
            int choice= 0;
            while (true) {
                try {
                    System.out.println("\n\nWelcome to restaurant manager!!" +
                            "\n1.check table availability" +
                            "\n0.Exit\n\nEnter your choice");
                    choice = input.nextInt();
                } catch (InputMismatchException e) {
                    input.nextLine();
                    continue;
                }
                break;
            }
            switch (choice) {
                case 1:
                    TableAvailability availability = this.seatAvailabilityChecker.checkTableAvailability(this.tableList,totalAvailableSeats);
                    System.out.println(availability.toString());
                    break;
                case 0:
                    stopApp = true;
                    break;
                default:
                    System.out.println("incorrect choice!! Please try again..");
            }
            if (stopApp) {
                break;
            }
        }
    }
}
