package pt.isel.ls.handler.booking.getall;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.model.Booking;

import java.util.List;

public class GetBookingView implements ResultView {
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
