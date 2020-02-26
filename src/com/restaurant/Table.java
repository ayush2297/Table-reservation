package com.restaurant;

public class Table {
    private int tableNo;
    private int numberOfSeats;
    private int availableSeats;

    public Table(int tableNo, Integer numberOfSeats) {
        this.tableNo = tableNo;
        this.numberOfSeats = numberOfSeats;
        this.availableSeats = numberOfSeats;
    }

    public int getTableNo() {
        return tableNo;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return " Table "+ tableNo + " ,";
    }
}
