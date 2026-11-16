
package main.model;

public class FormulaContent implements CellContent {

    private final String content;

    public FormulaContent(String content) {
        if (content == null || !content.startsWith("=")) {
            throw new IllegalArgumentException(
                    "A formula must start with '='"
            );
        }

        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
