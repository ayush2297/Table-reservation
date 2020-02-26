package com.restaurant;

public class Table {
    private int tableNo;
    private Integer numberOfSeats;

    public Table(int tableNo, Integer numberOfSeats) {
        this.tableNo = tableNo;
        this.numberOfSeats = numberOfSeats;
    }

    public int getTableNo() {
        return tableNo;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    @Override
    public String toString() {
        return " Table "+ tableNo + " ,";
    }
}
