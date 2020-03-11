package pt.isel.ls.model;

public class Rooms {
    private String name;
    private String location;
    private Integer capacity;
    private String labels;

    public Rooms(String name, String location, Integer capacity, String labels) {
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

    public String getLabels() {
        return labels;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String toString() {
        return String.format("RoomInfo: name %s , loc %s , cap %d , lab %s", name, location, capacity, labels);
    }


}
