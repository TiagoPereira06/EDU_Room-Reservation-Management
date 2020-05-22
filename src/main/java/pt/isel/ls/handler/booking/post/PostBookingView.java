package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.result.View;

public class PostBookingView extends View {
    private final String model;

    public PostBookingView(String postedBookingId) {
        this.model = postedBookingId;
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
