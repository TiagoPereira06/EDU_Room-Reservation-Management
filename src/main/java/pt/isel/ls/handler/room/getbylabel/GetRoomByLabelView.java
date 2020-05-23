package pt.isel.ls.handler.room.getbylabel;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Element;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Room;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetRoomByLabelView extends View {
    private final List<Room> model;
    private final String labelName;

    public GetRoomByLabelView(List<Room> roomsByLabel, String labelName) {
        this.model = roomsByLabel;
        this.labelName = labelName;
    }

    @Override
    public String name() {
        return "rooms with " + labelName;
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        setTable()
                )
        ).build();
    }

    private Element setTable() {
        return Element.table(
                tr(
                        th(text("Name")),
                        th(text("Location")),
                        th(text("Capacity")),
                        th(text("Description"))
                ),
                tableData()
        ).addAttribute("border", "1");

    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (Room room : model) {
            list.add(
                    tr(
                            td(anchor(text(room.getName()))),
                            td(text(room.getLocation())),
                            td(text(String.valueOf(room.getCapacity()))),
                            td(text(room.getDescription()))
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
