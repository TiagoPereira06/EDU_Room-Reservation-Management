package pt.isel.ls.handler.booking.getbyroom;

import pt.isel.ls.handler.View;
import pt.isel.ls.model.Booking;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.formatDateToString;

public class GetBookingByRoomView extends View {
    private final List<Booking> model;

    public GetBookingByRoomView(List<Booking> roomBookings) {
        this.model = roomBookings;
    }

    @Override
    public String name() {
        return String.format("%s's Bookings", model.get(0).getRoomName());
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name())),
                        nav(setNavBar())

                ),
                body(
                        h1(text(name())),
                        setTable()
                )
        ).build();
    }

    private Node setNavBar() {
        return homeButton();
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
                            td(text(owner)),
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
