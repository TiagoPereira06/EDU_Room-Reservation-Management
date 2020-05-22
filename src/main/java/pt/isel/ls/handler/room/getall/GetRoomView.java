package pt.isel.ls.handler.room.getall;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Element;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetRoomView extends View {
    private final List<Room> model;

    public GetRoomView(List<Room> allRooms) {
        this.model = allRooms;
    }

    @Override
    public String name() {
        return "rooms";
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
                        button("Rooms Search", "/rooms/search"),
                        homeButton()
                )
        ).build();
    }

    private Element setTable() {
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
            String name = room.getName();
            String location = room.getLocation();
            String capacity = String.valueOf(room.getCapacity());
            String description = room.getDescription();

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
            Label l = labels.get(i);
            String name = l.getName();
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
