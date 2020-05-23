package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class DeleteBookingView extends View {
    private final String model;

    public DeleteBookingView(String deletedBookingId) {
        this.model = deletedBookingId;
    }

    @Override
    public String name() {
        return model + " booking deleted";
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
