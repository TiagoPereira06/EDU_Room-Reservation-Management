package pt.isel.ls.handler.booking.getbyid;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.model.Booking;

public class GetBookingByIdView extends View {

    private final Booking model;

    public GetBookingByIdView(Booking bookingResult) {
        this.model = bookingResult;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String htmlOutput() {
        return null;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
