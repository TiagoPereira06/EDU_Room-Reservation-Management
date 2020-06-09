package pt.isel.ls.handler.booking.getall;

import pt.isel.ls.handler.View;
import pt.isel.ls.model.Booking;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.formatDateToString;

public class GetBookingView extends View {
    private final List<Booking> model;

    public GetBookingView(List<Booking> allBookings) {
        this.model = allBookings;
    }


    @Override
    public String name() {
        return "all bookings";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        setTable(),
                        homeButton()
                )
        ).build();
    }


    private Element setTable() {
        return Element.table(
                tr(
                        th(text("Id")),
                        th(text("Owner")),
                        th(text("Room")),
                        th(text("Begin")),
                        th(text("End"))
                ),
                tableData()
        ).addAttribute("border", "1");

    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (Booking booking : model) {
            String id = String.valueOf(booking.getId());
            String owner = booking.getReservationOwner();
            String room = booking.getRoomName();
            String begin = formatDateToString(booking.getBeginTime());
            String end = formatDateToString(booking.getEndTime());
            list.add(
                    tr(
                            td(anchor(text(id)).addAttribute("href", String.format("/rooms/%s/bookings/%s", room, id))),
                            td(anchor(text(owner)).addAttribute("href", String.format("/users/%s", owner))),
                            td(anchor(text(room)).addAttribute("href", String.format("/rooms/%s", room))),
                            td(text(begin)),
                            td(text(end))
                    )
            );
        }
        return list;
    }

    @Override
    public String plainOutput() {
        return model.toString();
    }

}
