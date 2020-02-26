package com.restaurant;

public class Main {

    public static void main(String[] args) {
        TableRegistration tableRegistration = new TableRegistration();
        int noOfTables = tableRegistration.addTableCount();
        tableRegistration.enterTableDetails(noOfTables);
    }
}
