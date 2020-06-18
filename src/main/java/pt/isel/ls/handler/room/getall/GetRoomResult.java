package pt.isel.ls.handler.room.getall;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class GetRoomResult extends Result {
    private final List<Room> model;

    public GetRoomResult(List<Room> allRooms) {
        this.model = allRooms;
    }


    @Override
    public String name() {
        return "Rooms";
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

    private Node[] setNavBar() {
        List<Node> navItems = new ArrayList<>();
        navItems.add(homeButton());
        navItems.add(text(" | "));
        navItems.add(button("Rooms Search", "/rooms/search"));
        return navItems.toArray(new Node[0]);
    }

    private Node setTable() {
        if (model.size() == 0) {
            return text("No Rooms !");
        }
        return Element.table(
                tr(
                        th(text("Name")),
                        th(text("Location")),
                        th(text("Capacity")),
                        th(text("Description")),
                        th(text("Label(s)"))
                ),
                tableData()
        ).addAttribute("border", "1");

    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (Room room : model) {
            final String name = room.getName();
            final String location = room.getLocation();
            final String capacity = String.valueOf(room.getCapacity());
            final String description = room.getDescription();

            list.add(
                    tr(
                            td(anchor(text(name)).addAttribute("href", String.format("/rooms/%s", name))),
                            td(text(location)),
                            td(text(capacity)),
                            td(text(description)),
                            formatLabelsRow(room.getLabels())
                    )
            );
        }
        return list;
    }

    private Node formatLabelsRow(List<Label> labels) {
        return td(formatLabels(labels));
    }

    private Node[] formatLabels(List<Label> labels) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < labels.size(); ++i) {
            final Label l = labels.get(i);
            final String name = l.getName();
            nodes.add(anchor(text(name)).addAttribute("href", String.format("/labels/%s", name)));
            if (i != labels.size() - 1) {
                nodes.add(text("  "));
            }
        }
        return nodes.toArray(new Node[0]);
    }

    @Override
    public String plainOutput() {
        return model.toString();
    }

}
