package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.Result;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostBookingResult extends Result {
    private final String model;

    public PostBookingResult(String postedBookingId) {
        this.model = postedBookingId;
    }

    @Override
    public String name() {
        return model + " booking posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("booking id: " + model))

        ).build();
    }

    @Override
    public String plainOutput() {
        return model;
    }

}
