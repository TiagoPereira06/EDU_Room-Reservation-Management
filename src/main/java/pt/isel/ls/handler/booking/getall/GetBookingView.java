package pt.isel.ls.handler.booking.getall;


import pt.isel.ls.handler.result.View;
import pt.isel.ls.model.Booking;

import java.util.List;

public class GetBookingView extends View {
    private final List<Booking> model;

    public GetBookingView(List<Booking> allBookings) {
        this.model = allBookings;
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
