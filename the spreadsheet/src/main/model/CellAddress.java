
package main.model;

import java.util.Locale;
import java.util.Objects;

public class CellAddress {

    private final String column;
    private final int row;

    // Constructor
    public CellAddress(String column, int row) {
        if (column == null || !column.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid column");
        }

        if (row < 1) {
            throw new IllegalArgumentException("Row must be positive");
        }

        this.column = column.toUpperCase(Locale.ROOT);
        this.row = row;
    }

    // Getters
    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    // Return address as A1, B2, etc.
    @Override
    public String toString() {
        return column + row;
    }

    // Compare two cell addresses
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CellAddress)) return false;

        CellAddress other = (CellAddress) obj;

        return row == other.row &&
                column.equals(other.column);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
