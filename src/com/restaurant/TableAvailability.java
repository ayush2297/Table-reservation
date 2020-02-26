package com.restaurant;

import java.util.ArrayList;
import java.util.List;

public class TableAvailability {
    private List<Table> tableList;
    private String isAvailable;

    public TableAvailability(List<Table> tableList, boolean isAvailable) {
        this.tableList = tableList;
        this.isAvailable = "Y";
    }

    public TableAvailability(boolean unavailable) {
        this.tableList = new ArrayList<>();
        this.isAvailable = "N";
    }

    @Override
    public String toString() {
        return this.isAvailable + this.tableList.toString();
    }
}
