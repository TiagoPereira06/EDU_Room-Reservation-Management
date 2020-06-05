package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class PostBookingView extends View {
    private String model;

    public PostBookingView(String postedBookingId) {
        this.model = postedBookingId;
    }

    public PostBookingView() {
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

    @Override
    public void setModel(Model resultModel) {
        this.model = (String) resultModel.getPrimaryData();
    }
}
