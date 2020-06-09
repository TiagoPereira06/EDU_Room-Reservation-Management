package pt.isel.ls.userinterfaces.format.html.htmlemitter;

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
