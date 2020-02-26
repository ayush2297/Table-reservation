package com.restaurant;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RestaurantManagerMenu {
    private static final String AVAILABLE = "Y";
    private static final String UNAVAILABLE = "N";

    private TableRegistration tableRegistration;
    private Scanner input;

    public RestaurantManagerMenu() {
        this.input = new Scanner(System.in);
        this.tableRegistration = new TableRegistration();
    }

    public void displayMenu() {
        int noOfTables = this.tableRegistration.addTableCount();
        this.tableRegistration.enterTableDetails(noOfTables);
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
                    TableAvailability availability = this.tableRegistration.checkTableAvailability();
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
