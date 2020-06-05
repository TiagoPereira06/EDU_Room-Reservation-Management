package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class DeleteBookingView extends View {
    private String model;

    public DeleteBookingView(String deletedBookingId) {
        this.model = deletedBookingId;
    }

    public DeleteBookingView() {

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

    @Override
    public void setModel(Model resultModel) {
        this.model = (String) resultModel.getPrimaryData();
    }

}
