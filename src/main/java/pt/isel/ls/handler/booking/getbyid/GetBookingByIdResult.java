package pt.isel.ls.handler.booking.getbyid;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Booking;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.formatDateToString;

public class GetBookingByIdResult extends Result {

    private final Booking model;
    private final String roomName;
    private final String owner;

    public GetBookingByIdResult(Booking data) {
        this.model = data;
        roomName = model.getRoomName();
        owner = model.getReservationOwner();
    }

    @Override
    public String name() {
        return "booking " + model.getId() + " details";
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
                        dl(listFormat())
                )
        ).build();
    }

    private Node[] setNavBar() {
        List<Node> navItems = new ArrayList<>();
        navItems.add(homeButton());
        navItems.add(text(" | "));
        navItems.add(button(String.format("%s's Bookings", roomName),
                String.format("/rooms/%s/bookings", roomName)));
        return navItems.toArray(new Node[0]);
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold((text("Id:")))));
        listItems.add(dd(text(String.valueOf(model.getId()))));
        listItems.add(li(bold((text("Owner:")))));
        listItems.add(dd(anchor(text(owner)).addAttribute("href", String.format("/users/%s", owner))));
        listItems.add(li(bold((text("Room:")))));
        listItems.add(dd(anchor(text(roomName)).addAttribute("href", String.format("/rooms/%s", roomName))));
        listItems.add(li(bold((text("Begin:")))));
        listItems.add(dd(text(formatDateToString(model.getBeginTime()))));
        listItems.add(li(bold((text("End:")))));
        listItems.add(dd(text(formatDateToString(model.getEndTime()))));
        return listItems;
    }

    @Override
    public String plainOutput() {
        return model.toString();
    }


}
