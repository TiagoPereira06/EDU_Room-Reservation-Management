package pt.isel.ls.handler.booking.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class PostBookingResult extends Result {
    public PostBookingResult(List<List<String>> bookingResult) {
        super(bookingResult);
    }

    @Override
    public List<String> columns() {
        return List.of("Booking ID");
    }

    @Override
    public String description() {
        return "POST Booking";
    }
}
