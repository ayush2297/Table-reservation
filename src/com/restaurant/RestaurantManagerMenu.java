package com.restaurant;

import java.util.Scanner;

public class RestaurantManagerMenu {
    private TableRegistration tableRegistration;
    private Scanner input;

    public RestaurantManagerMenu() {
        this.input = new Scanner(System.in);
        this.tableRegistration = new TableRegistration();
    }

    public void displayMenu() {
        int noOfTables = this.tableRegistration.addTableCount();
        this.tableRegistration.enterTableDetails(noOfTables);
        while (true) {
            boolean stopApp = false;
            System.out.println("\n\nWelcome to restaurant manager!!" +
                    "\n1.show tables" +
                    "\n2.check table availability" +
                    "\n0.Exit\n\nEnter your choice");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    this.tableRegistration.showTables();
                    break;
                case 2:
                    System.out.println("this feature is unavailable right now");
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
