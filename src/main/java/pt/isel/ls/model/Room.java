package pt.isel.ls.model;

import java.util.List;

public class Room {
    private String name;
    private String location;
    private Integer capacity;
    private List<String> labels;

    public Room(String name, String location, Integer capacity, List<String> labels) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.labels = labels;
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

    public List<String> getLabels() {
        return labels;
    }

    public String toString() {
        return String.format("RoomInfo: name %s , loc %s , cap %d , lab %s", name, location, capacity, labels);
    }


}
