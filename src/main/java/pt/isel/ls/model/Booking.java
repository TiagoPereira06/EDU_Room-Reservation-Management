package pt.isel.ls.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Booking {
    static final String pattern = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
    private final String reservationOwner;
    private final String roomName;
    private int id;
    private Date beginTime;
    private Date endTime;

    public Booking(String owner, String roomName, String beginTime, String endTime) {
        this.reservationOwner = owner;
        this.roomName = roomName;
        try {
            this.beginTime = dateFormat.parse(beginTime);
            this.endTime = dateFormat.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getReservationOwner() {
        return reservationOwner;
    }

    public String getRoomName() {
        return roomName;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public List<String> toList() {
        return List.of(reservationOwner, roomName, dateFormat.format(beginTime), dateFormat.format(endTime));
    }

    @Override
    public String toString() {
        return "BOOKING INFO : Owner -> " + reservationOwner + " ; Room Name -> " + roomName + " ; Begin/End -> "
                + dateFormat.format(beginTime) + " / " + dateFormat.format(endTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
