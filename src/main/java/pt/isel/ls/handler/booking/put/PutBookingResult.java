package pt.isel.ls.handler.booking.put;

import pt.isel.ls.handler.Result;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PutBookingResult extends Result {
    private final String model;

    public PutBookingResult(String roomId) {
        this.model = roomId;
    }


    @Override
    public String name() {
        return model + " room put";
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
