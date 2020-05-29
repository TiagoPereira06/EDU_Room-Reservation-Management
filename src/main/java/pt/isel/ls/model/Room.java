package pt.isel.ls.model;

import java.util.LinkedList;
import java.util.List;

public class Room {
    private final String name;
    private String location;
    private Integer capacity;
    private String description;
    private List<Label> labels;

    public Room(String name, String location, Integer capacity, String description) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.description = description;
        labels = new LinkedList<>();
    }

    public Room(String roomName) {
        this.name = roomName;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<String> toList(boolean labelsFlag) {
        List<String> base = new java.util.ArrayList<>(List.of(name, location, String.valueOf(capacity), description));
        if (!labels.isEmpty() && labelsFlag) {
            for (Label l : labels) {
                base.add(l.toString());
            }
        }
        return base;
    }

    @Override
    public String toString() {
        String s = String.format("ROOM INFO: Name -> %s , Location -> %s , Capacity -> %d , Description -> %s ",
                name, location, capacity, description);
        if (!labels.isEmpty()) {
            return s + String.format(", Labels -> %s", labels);
        }
        return s;
    }
}
