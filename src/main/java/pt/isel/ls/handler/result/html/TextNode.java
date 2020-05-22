package pt.isel.ls.handler.result.html;

public class TextNode implements Node {

    private final String text;

    public TextNode(String text) {
        this.text = text;
    }

    @Override
    public String build() {
        return text;
    }
}
