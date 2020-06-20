package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.handler.Result;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class DeleteBookingResult extends Result {
    private final String model;

    public DeleteBookingResult(String deletedBookingId) {
        this.model = deletedBookingId;
    }

    @Override
    public String name() {
        return model + " Booking Deleted";
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
