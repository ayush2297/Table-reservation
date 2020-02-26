package com.restaurant;
import java.util.ArrayList;
import java.util.List;

public class TableRegistration {

    private List<Table> tableRegistry;

    public TableRegistration(List<Table> tableRegistry) {
        tableRegistry = new ArrayList<>();
    }

    public void registerTables (int numberOfTables, List<Integer> numberOfSeatsPerTable) {
        for (int tableNo = 0; tableNo < numberOfTables; tableNo++) {
            Table table = new Table(tableNo, numberOfSeatsPerTable.get(tableNo));
            tableRegistry.add(table);
        }
    }

}
