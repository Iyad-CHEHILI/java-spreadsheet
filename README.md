
# Java Spreadsheet

**Software Architecture Project Deliverable 1**

## Description

This project is a basic spreadsheet application developed in Java for the Software Architecture course.

The application uses object-oriented programming principles and provides a console-based interface to manage spreadsheets.

## Deliverable 1 Implemented Features

- Spreadsheet domain model using Java classes and interfaces.
- Support for numeric, text, and formula cell contents.
- Set and view the content of individual cells.
- Load spreadsheets from S2V files.
- Save spreadsheets in S2V (Semicolon Separated Values) format.
- Console-based menu for user interaction.

**Note:** Formula evaluation is not implemented in this deliverable. Formulas are stored and displayed as text without being calculated.

## Project Structure

```text
src/
└── main/
    ├── Main.java
    ├── model/
    │   ├── CellAddress.java
    │   ├── CellContent.java
    │   ├── NumericContent.java
    │   ├── TextContent.java
    │   ├── FormulaContent.java
    │   ├── Cell.java
    │   └── Spreadsheet.java
    ├── persistence/
    │   └── S2VStorage.java
    └── ui/
        └── ConsoleUI.java
```

## How to Run

1. Download or clone this repository.
2. Open the project in IntelliJ IDEA.
3. Configure a compatible Java JDK (Java 17 or later).
4. Run `Main.java`.
5. Use the console menu to interact with the spreadsheet.

## Available Operations

The application provides the following options:

1. Create a new spreadsheet.
2. Set cell content.
3. View cell content.
4. Load a spreadsheet from a file.
5. Save a spreadsheet to a file.
0. Exit.

## Example

The following spreadsheet:

| Cell | Content |
|------|---------|
| A1 | 10 |
| B1 | Hello |
| C1 | =A1*2 |

Can be stored in an S2V file as:

```text
10;Hello;=A1*2
```

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- IntelliJ IDEA
- Git and GitHub

## Future Improvements

Future deliverables will include:

- Formula evaluation using expression trees.
- Mathematical operations and functions.
- Cell references and dependencies.
- Circular dependency detection.
- Formula parsing and ranges.

## Author

Iyad Chehili
