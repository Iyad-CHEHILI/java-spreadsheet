
package main.persistence;

import main.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class S2VStorage {

    // Save spreadsheet contents to an S2V file
    public void save(Spreadsheet spreadsheet, Path path)
            throws IOException {

        Map<CellAddress, Cell> cells = spreadsheet.getCells();

        int maxRow = 0;
        int maxColumn = 0;

        for (CellAddress address : cells.keySet()) {
            maxRow = Math.max(maxRow, address.getRow());
            maxColumn = Math.max(maxColumn,
                    columnToNumber(address.getColumn()));
        }

        List<String> lines = new ArrayList<>();

        for (int row = 1; row <= maxRow; row++) {
            List<String> fields = new ArrayList<>();

            for (int col = 1; col <= maxColumn; col++) {
                CellAddress address = new CellAddress(
                        numberToColumn(col), row);

                String content = spreadsheet
                        .getCellContent(address).getContent();

                // Convert function argument separators for S2V
                if (content.startsWith("=")) {
                    content = content.replace(';', ',');
                }

                fields.add(content);
            }

            // Remove trailing empty cells from the row
            while (!fields.isEmpty()
                    && fields.get(fields.size() - 1).isEmpty()) {
                fields.remove(fields.size() - 1);
            }

            lines.add(String.join(";", fields));
        }

        Files.write(path, lines);
    }

    // Load spreadsheet contents from an S2V file
    public Spreadsheet load(Path path) throws IOException {

        Spreadsheet spreadsheet = new Spreadsheet();
        List<String> lines = Files.readAllLines(path);

        for (int row = 0; row < lines.size(); row++) {
            String[] fields = lines.get(row).split(";", -1);

            for (int col = 0; col < fields.length; col++) {
                String content = fields[col];

                if (content.isEmpty()) {
                    continue;
                }

                CellAddress address = new CellAddress(
                        numberToColumn(col + 1), row + 1);

                CellContent cellContent;

                if (content.startsWith("=")) {
                    cellContent = new FormulaContent(
                            content.replace(',', ';'));
                } else {
                    try {
                        Double.parseDouble(content);
                        cellContent = new NumericContent(content);
                    } catch (NumberFormatException e) {
                        cellContent = new TextContent(content);
                    }
                }

                spreadsheet.setCellContent(address, cellContent);
            }
        }

        return spreadsheet;
    }

    // Convert column letters to a number: A=1, Z=26, AA=27
    private int columnToNumber(String column) {
        int result = 0;

        for (char c : column.toCharArray()) {
            result = result * 26 + (c - 'A' + 1);
        }

        return result;
    }

    // Convert a number back into letters: 1=A, 27=AA
    private String numberToColumn(int number) {
        StringBuilder result = new StringBuilder();

        while (number > 0) {
            number--;
            result.insert(0, (char) ('A' + number % 26));
            number /= 26;
        }

        return result.toString();
    }
}
