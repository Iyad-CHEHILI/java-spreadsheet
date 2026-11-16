
package main.model;

public class TextContent implements CellContent {

    private final String content;

    public TextContent(String content) {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
