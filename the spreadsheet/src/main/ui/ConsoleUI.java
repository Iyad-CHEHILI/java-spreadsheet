
package main.ui;

import main.model.*;
import main.persistence.S2VStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleUI {

    private Spreadsheet spreadsheet;
    private final S2VStorage storage;
    private final Scanner scanner;

    public ConsoleUI() {
        spreadsheet = new Spreadsheet();
        storage = new S2VStorage();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;

        while (running) {
            showMenu();
            String option = scanner.nextLine().trim();

            try {
                switch (option) {
                    case "1" -> newSpreadsheet();
                    case "2" -> setCellContent();
                    case "3" -> viewCellContent();
                    case "4" -> loadSpreadsheet();
                    case "5" -> saveSpreadsheet();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            }
        }

        System.out.println("Goodbye!");
    }

    private void showMenu() {
        System.out.println("\n========== SPREADSHEET ==========");
        System.out.println("1. Create new spreadsheet");
        System.out.println("2. Set cell content");
        System.out.println("3. View cell content");
        System.out.println("4. Load spreadsheet");
        System.out.println("5. Save spreadsheet");
        System.out.println("0. Exit");
        System.out.println("=================================");
        System.out.print("Choose an option: ");
    }

    private void newSpreadsheet() {
        spreadsheet = new Spreadsheet();
        System.out.println("New spreadsheet created.");
    }

    private void setCellContent() {
        System.out.print("Cell column (A, B, AA...): ");
        String column = scanner.nextLine().trim();

        System.out.print("Cell row (1, 2, 3...): ");
        int row = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter cell content: ");
        String content = scanner.nextLine();

        CellAddress address = new CellAddress(column, row);
        CellContent cellContent;

        if (content.startsWith("=")) {
            cellContent = new FormulaContent(content);
        } else if (content.isEmpty()) {
            cellContent = new TextContent("");
        } else {
            try {
                Double.parseDouble(content);
                cellContent = new NumericContent(content);
            } catch (NumberFormatException e) {
                cellContent = new TextContent(content);
            }
        }

        spreadsheet.setCellContent(address, cellContent);
        System.out.println(address + " updated successfully.");
    }

    private void viewCellContent() {
        System.out.print("Cell column: ");
        String column = scanner.nextLine().trim();

        System.out.print("Cell row: ");
        int row = Integer.parseInt(scanner.nextLine().trim());

        CellAddress address = new CellAddress(column, row);

        String content = spreadsheet.getCellContent(address).getContent();
        System.out.println(address + " content: " + content);
    }

    private void loadSpreadsheet() throws IOException {
        System.out.print("Enter file path: ");
        Path path = Path.of(scanner.nextLine().trim());

        spreadsheet = storage.load(path);
        System.out.println("Spreadsheet loaded successfully.");
    }

    private void saveSpreadsheet() throws IOException {
        System.out.print("Enter file path: ");
        Path path = Path.of(scanner.nextLine().trim());

        storage.save(spreadsheet, path);
        System.out.println("Spreadsheet saved successfully.");
    }
}
