package pt.isel.ls.userinterfaces.format.html.htmlemitter;

public class Attribute {

    private final String name;
    private final String value;

    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return name + "=" + value;
    }
}
