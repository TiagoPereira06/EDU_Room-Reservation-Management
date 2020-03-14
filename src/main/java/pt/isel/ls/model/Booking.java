package pt.isel.ls.model;

public class Booking {
    private String reservationOwner;
    private String roomName;
    private String beginTime;
    private String endTime;

    public Booking(String owner, String roomName, String beginTime, String endTime) {
        this.reservationOwner = owner;
        this.roomName = roomName;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public String getReservationOwner() {
        return reservationOwner;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String toString() {
        return "Booking Info : Owner -> " + reservationOwner + " Room Name -> " + roomName + " Begin/End -> "
                + beginTime + " / " + endTime;
    }
}
