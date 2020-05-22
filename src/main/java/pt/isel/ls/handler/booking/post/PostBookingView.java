package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.ResultView;

public class PostBookingView implements ResultView {
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
