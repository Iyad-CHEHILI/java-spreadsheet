
package main.model;

public class NumericContent implements CellContent {

    private final String content;

    public NumericContent(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        // Check that content is a valid number
        try {
            Double.parseDouble(content);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid numeric content");
        }

        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
