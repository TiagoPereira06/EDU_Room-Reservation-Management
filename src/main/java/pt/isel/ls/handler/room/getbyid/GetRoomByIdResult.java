package pt.isel.ls.handler.room.getbyid;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Booking;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.formatDateToString;

public class GetRoomByIdResult extends Result {
    private final Room room;
    private final List<Booking> bookings;

    public GetRoomByIdResult(Room roomById, List<Booking> bookingsResult) {
        this.room = roomById;
        this.bookings = bookingsResult;
    }


    @Override
    public String name() {
        return room.getName() + " room details";
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
                        dl(listFormat()),
                        h3(text(String.format("%s Booking(s)", room.getName()))),
                        setBookingsTable()
                )
        ).build();
    }

    private Node[] setNavBar() {
        List<Node> navItems = new ArrayList<>();
        navItems.add(homeButton());
        navItems.add(text(" | "));
        navItems.add(button("All Rooms", "/rooms"));
        navItems.add(text(" | "));
        navItems.add(button("Rooms Search", "/rooms/search"));
        navItems.add(text(" | "));
        navItems.add(button("Create Booking", String.format("/rooms/%s/bookings/create", room.getName())));
        return navItems.toArray(new Node[0]);
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold(text("Name:"))));
        listItems.add(dd(text(room.getName())));
        listItems.add(li(bold(text("Location:"))));
        listItems.add(dd(text(room.getLocation())));
        listItems.add(li(bold(text("Capacity:"))));
        listItems.add(dd(text(String.valueOf(room.getCapacity()))));
        listItems.add(li(bold((text("Description:")))));
        listItems.add(dd(text(room.getDescription())));
        listItems.add(li(bold((text("Label(s):")))));
        for (Label l : room.getLabels()) {
            String name = l.getName();
            listItems.add(dd(anchor(text(name)).addAttribute("href", String.format("/labels/%s", name))));
        }
        return listItems;
    }

    private Node setBookingsTable() {
        if (bookings.size() == 0) {
            return text("No Bookings !");
        } else {
            return Element.table(
                    tr(
                            th(text("Id")),
                            th(text("Owner")),
                            th(text("Begin")),
                            th(text("End"))
                    ),
                    tableData()
            ).addAttribute("border", "1");
        }
    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (Booking booking : bookings) {
            String id = String.valueOf(booking.getId());
            String owner = booking.getReservationOwner();
            String begin = formatDateToString(booking.getBeginTime());
            String end = formatDateToString(booking.getEndTime());
            list.add(
                    tr(
                            td(anchor(text(id)).addAttribute("href", String.format("/rooms/%s/bookings/%s", room.getName(), id))),
                            td(anchor(text(owner)).addAttribute("href", String.format("/users/%s", owner))),
                            td(text(begin)),
                            td(text(end))
                    )
            );
        }
        return list;
    }


    @Override
    public String plainOutput() {
        return room.toString();
    }

}
