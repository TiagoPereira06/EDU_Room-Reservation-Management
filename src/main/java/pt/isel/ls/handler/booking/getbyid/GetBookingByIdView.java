package pt.isel.ls.handler.booking.getbyid;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Booking;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;
import static pt.isel.ls.utils.UtilMethods.formatDateToString;

public class GetBookingByIdView extends View {

    private final Booking model;
    private final String id;
    private final String owner;
    private final String room;
    private final String begin;
    private final String end;


    public GetBookingByIdView(Booking bookingResult) {
        this.model = bookingResult;
        this.id = String.valueOf(model.getId());
        this.owner = model.getReservationOwner();
        this.room = model.getRoomName();
        this.begin = formatDateToString(model.getBeginTime());
        this.end = formatDateToString(model.getEndTime());
    }

    @Override
    public String name() {
        return "booking " + model.getId() + " details";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        dl(listFormat()),
                        button("Bookings By Room", String.format("/rooms/%s/bookings", room)),
                        homeButton()
                )
        ).build();
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold((text("Id:")))));
        listItems.add(dd(text(id)));
        listItems.add(li(bold((text("Owner:")))));
        listItems.add(dd(anchor(text(owner)).addAttribute("href", String.format("/users/%s", owner))));
        listItems.add(li(bold((text("Room:")))));
        listItems.add(dd(anchor(text(room)).addAttribute("href", String.format("/rooms/%s", room))));
        listItems.add(li(bold((text("Begin:")))));
        listItems.add(dd(text(begin)));
        listItems.add(li(bold((text("End:")))));
        listItems.add(dd(text(end)));
        return listItems;
    }

    @Override
    public String plainOutput() {
        return model.toString();
    }
}
