package pt.isel.ls.model;

import java.util.List;

public class Label {
    private final String name;

    public Label(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> parsePropertiesList() {
        return List.of(name);
    }

    @Override
    public String toString() {
        return (name);
    }
}
