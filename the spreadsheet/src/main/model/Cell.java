
package main.model;

public class Cell {

    private final CellAddress address;
    private CellContent content;

    public Cell(CellAddress address, CellContent content) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        this.address = address;
        this.content = content;
    }

    public CellAddress getAddress() {
        return address;
    }

    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        this.content = content;
    }
}
