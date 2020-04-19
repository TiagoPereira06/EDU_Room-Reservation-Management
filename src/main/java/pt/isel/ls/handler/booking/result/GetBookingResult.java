package pt.isel.ls.handler.booking.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class GetBookingResult extends Result {

    public GetBookingResult(List<List<String>> bookingResult) {
        super(bookingResult);
    }

    @Override
    public List<String> columns() {
        return List.of("Reservation Owner", "Room Name", "Begin Time", "End Time");
    }

    @Override
    public String description() {
        return "GET Booking";
    }

}
