package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;
import static pt.isel.ls.handler.result.html.Element.text;

public class PostBookingView extends View {
    private final String model;

    public PostBookingView(String postedBookingId) {
        this.model = postedBookingId;
    }

    @Override
    public String name() {
        return model +" booking posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("booking id: "+model))

        ).build();
    }

    @Override
    public String plainOutput() {
        return model;
    }
}
