
package main.model;

import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {

    private final Map<CellAddress, Cell> cells;

    public Spreadsheet() {
        cells = new HashMap<>();
    }

    // Add or modify a cell's content
    public void setCellContent(CellAddress address, CellContent content) {
        if (address == null || content == null) {
            throw new IllegalArgumentException(
                    "Address and content cannot be null"
            );
        }

        Cell cell = cells.get(address);

        if (cell == null) {
            cell = new Cell(address, content);
            cells.put(address, cell);
        } else {
            cell.setContent(content);
        }
    }

    // Get the content of a cell
    public CellContent getCellContent(CellAddress address) {
        Cell cell = cells.get(address);

        if (cell == null) {
            return new TextContent("");
        }

        return cell.getContent();
    }

    // Get all stored cells (useful for saving)
    public Map<CellAddress, Cell> getCells() {
        return Map.copyOf(cells);
    }
}
