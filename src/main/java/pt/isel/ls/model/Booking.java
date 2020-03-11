package pt.isel.ls.model;

public class Booking {
    private Integer begin;
    private Integer end;
    private String owner;

    public Booking(Integer begin, Integer end, String owner) {
        this.begin = begin;
        this.end = end;
        this.owner = owner;
    }

    public Integer getBegin() {
        return begin;
    }

    public Integer getEnd() {
        return end;
    }

    public String getOwner() {
        return owner;
    }

    public String toString() {
        return String.format("Booking information: begin %d , end %d , reservation owner %s", begin, end, owner);
    }
}
