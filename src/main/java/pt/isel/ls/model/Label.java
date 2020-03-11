package pt.isel.ls.model;

public class Label {
    private String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("Label: %s", name);
    }
}
