package com.restaurant;

public class Table {
    private final int tableNo;
    private final Integer numberOfSeats;

    public Table(int tableNo, Integer numberOfSeats) {
        this.tableNo = tableNo;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "\nTable {" +
                "\n\ttableNo=" + tableNo +
                ",\n\tnumberOfSeats=" + numberOfSeats +
                "\n}";
    }
}
