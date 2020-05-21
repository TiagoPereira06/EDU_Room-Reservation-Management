package pt.isel.ls.handler.result.html;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

public class Element implements Node {

    private final String name;
    private final List<Attribute> attributes = new LinkedList<>();
    private final List<Node> nodes = new LinkedList<>();

    public Element(String name) {
        this.name = name;
    }

    public Element addNode(Node node) {
        nodes.add(node);
        return this;
    }

    public Element addNode(Iterable<Node> it) {
        for (Node node : it) {
            nodes.add(node);
        }
        return this;
    }

    public Element addNode(Node... n) {
        for (Node node : n) {
            this.addNode(node);
        }
        return this;
    }


    public Element addAttribute(String name, String value) {
        attributes.add(new Attribute(name,value));
        return this;
    }


    public static Element html(Node... node) {

        return new Element("html").addNode(node);
    }

    public static Element head(Node... node) {

        return new Element("head").addNode(node);
    }

    public static Element title(Node... node) {
        return new Element("title").addNode(node);
    }

    public static Element body(Node... node) {
        return new Element("body").addNode(node);
    }

    public static Element h1(Node... node) {
        return new Element("h1").addNode(node);
    }

    public static Element h2(Node... node) {
        return new Element("h2").addNode(node);
    }

    public static Element h3(Node... node) {
        return new Element("h3").addNode(node);
    }

    public static Element table(List<Node> n, Node node) {
        return new Element("table").addNode(n).addNode(node);
    }

    public static Element tr(Node... node) {
        return new Element("tr").addNode(node);
    }

    public static Element th(Node node) {
        return new Element("th").addNode(node);
    }

    public static Element td(Node node) {
        return new Element("td").addNode(node);
    }

    public static Element ul(Node node) {
        return new Element("ul").addNode(node);
    }

    public static Element ul(List<Node> node) {
        return new Element("ul").addNode(node);
    }

    public static Element li(Node node) {
        return new Element("li").addNode(node);
    }

    public static Element paragraph() {
        return new Element("paragraph");
    }

    public static Node text(String txt) {
        return new TextNode(txt);
    }

    public static Element append(String name, String value, Node node) {
        return new Element("append").addAttribute(name, value).addNode(node);
    }






    @Override
    public void print(PrintStream out, int local) {
        for (int i = 0; i < local;i++) {
            out.print('\t');
        }
        out.print("<");
        out.print(name);
        for (Attribute attr : attributes) {
            out.print(" ");
            out.print(attr);
        }
        out.print(">");
        for (Node node : nodes) {
            node.print(out,local + 1);
            out.println();
        }
        for (int i = 0; i < local; i++) {
            out.print('\t');
        }
        out.print("</");
        out.print(name);
        out.print(">");
    }
}
