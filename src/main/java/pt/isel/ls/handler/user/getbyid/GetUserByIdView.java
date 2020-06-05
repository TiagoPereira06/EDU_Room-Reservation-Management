package pt.isel.ls.handler.user.getbyid;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Element;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Booking;
import pt.isel.ls.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;
import static pt.isel.ls.utils.UtilMethods.formatDateToString;

public class GetUserByIdView extends View {
    private User user;
    private List<Booking> bookingsOwned;

    public GetUserByIdView(User userById, List<Booking> bookingResult) {
        this.user = userById;
        this.bookingsOwned = bookingResult;
    }

    public GetUserByIdView() {

    }

    @Override
    public String name() {
        return String.format("%s's Information", user.getName());
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
                        h3(text(String.format("%s's Bookings", user.getName()))),
                        setTable()
                )
        ).build();
    }

    private Node[] setNavBar() {
        List<Node> navItems = new ArrayList<>();
        navItems.add(homeButton());
        navItems.add(text(" | "));
        navItems.add(button("All Users", "/users"));
        return navItems.toArray(new Node[0]);
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold((text("Name:")))));
        listItems.add(dd(text(user.getName())));
        listItems.add(li(bold((text("Email:")))));
        listItems.add(dd(text(user.getEmail())));
        return listItems;
    }

    private Node setTable() {
        if (bookingsOwned.size() == 0) {
            return text("No Bookings !");
        } else {
            return Element.table(
                    tr(
                            th(text("Id")),
                            th(text("Room")),
                            th(text("Begin")),
                            th(text("End"))
                    ),
                    tableData()
            ).addAttribute("border", "1");
        }
    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (Booking booking : bookingsOwned) {
            String id = String.valueOf(booking.getId());
            String room = booking.getRoomName();
            String begin = formatDateToString(booking.getBeginTime());
            String end = formatDateToString(booking.getEndTime());
            list.add(
                    tr(
                            td(anchor(text(id)).addAttribute("href", String.format("/rooms/%s/bookings/%s", room, id))),
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
        return user.toString();
    }

    @Override
    public void setModel(Model resultModel) {
        this.user = (User) resultModel.getPrimaryData();
        this.bookingsOwned = (List<Booking>) resultModel.getSecondaryData();
    }
}
