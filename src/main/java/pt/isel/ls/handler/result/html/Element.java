package pt.isel.ls.handler.result.html;

import java.util.LinkedList;
import java.util.List;

public class Element implements Node {

    private final String name;
    private final List<Attribute> attributes = new LinkedList<>();
    private final List<Node> nodes = new LinkedList<>();

    public Element(String name) {
        this.name = name;
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

    public static Element nav(Node... node) {
        return new Element("nav").addNode(node);
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

    public static Element div(Node... node) {
        return new Element("div").addNode(node);
    }

    public static Element form(Node... node) {
        return new Element("form").addNode(node);
    }

    public static Element label(Node... node) {
        return new Element("label").addNode(node);
    }

    public static Element input(Node... node) {
        return new Element("input").addNode(node);
    }


    public static Element table(Node node, List<Node> n) {
        return new Element("table").addNode(node).addNode(n);
    }

    public static Element tr(Node... node) {
        return new Element("tr").addNode(node);
    }

    public static Element th(Node node) {
        return new Element("th").addNode(node);
    }

    public static Element td(Node... node) {
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

    public static Element dd(Node... node) {
        return new Element("dd").addNode(node);
    }

    public static Element dt(Node node) {
        return new Element("dt").addNode(node);
    }

    public static Element dl(Node node) {
        return new Element("dl").addNode(node);
    }

    public static Element dl(List<Node> node) {
        return new Element("dl").addNode(node);
    }

    public static Element anchor(Node node) {
        return new Element("a").addNode(node);
    }

    public static Element bold(Node node) {
        return new Element("b").addNode(node);
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
        attributes.add(new Attribute(name, value));
        return this;
    }

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(name);
        for (Attribute attr : attributes) {
            sb.append(" ");
            sb.append(attr);
        }
        sb.append(">");
        for (Node node : nodes) {
            sb.append(node.build());
        }
        sb.append("</");
        sb.append(name);
        sb.append(">");
        return sb.toString();
    }
}
