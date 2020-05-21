
package pt.isel.ls.handler.result.html;

import java.io.PrintStream;

public class TextNode implements Node {

    private final String text;

    public TextNode(String text) {
        this.text = text;
    }


    @Override
    public void print(PrintStream out, int local) {
        for (int i = 0; i < local; i++) {
            out.print('\t');
        }
        out.print(text);
    }
}
