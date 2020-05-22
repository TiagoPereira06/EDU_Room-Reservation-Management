package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.handler.result.View;

public class DeleteBookingView extends View {
    private final String model;

    public DeleteBookingView(String deletedBookingId) {
        this.model = deletedBookingId;
    }

    @Override
    public String name() {
        return "Delete Booking";
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
