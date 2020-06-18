package pt.isel.ls.handler.label.getbyid;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class GetLabelByIdResult extends Result {
    private final Label model;
    private final List<Room> rooms;

    public GetLabelByIdResult(Label labelById, List<Room> roomsResult) {
        this.model = labelById;
        this.rooms = roomsResult;
    }


    @Override
    public String name() {
        return model.getName() + " Label Information";
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
        navItems.add(button("All Labels", "/labels"));
        return navItems.toArray(new Node[0]);
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold((text("Name:")))));
        listItems.add(dd(text(model.getName())));
        listItems.add(li(bold((text("Room(s) Compatible:")))));
        if (rooms.size() != 0) {
            for (Room m : rooms) {
                String name = m.getName();
                listItems.add(dd(anchor(text(name)).addAttribute("href", String.format("/rooms/%s", name))));

            }
        } else {
            listItems.add(dd(text("No Rooms !")));
        }
        return listItems;
    }


    @Override
    public String plainOutput() {
        return model.toString();
    }

}
