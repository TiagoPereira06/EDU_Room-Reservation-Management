package pt.isel.ls.handler.room.getbyid;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetRoomByIdView extends View {
    private final Room model;

    public GetRoomByIdView(Room roomById) {
        this.model = roomById;
    }

    @Override
    public String name() {
        return model.getName() + " room details";
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
                        button("Bookings", String.format("/rooms/%s/bookings", model.getName())),
                        homeButton(),
                        button("All Roms", "/rooms"),
                        button("Rooms Search", "/rooms/search")
                )
        ).build();
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold(text("Name:"))));
        listItems.add(dd(text(model.getName())));
        listItems.add(li(bold(text("Location:"))));
        listItems.add(dd(text(model.getLocation())));
        listItems.add(li(bold(text("Capacity:"))));
        listItems.add(dd(text(String.valueOf(model.getCapacity()))));
        listItems.add(li(bold((text("Description:")))));
        listItems.add(dd(text(model.getDescription())));
        listItems.add(li(bold((text("Label(s):")))));
        for (Label l : model.getLabels()) {
            String name = l.getName();
            listItems.add(dd(anchor(text(name)).addAttribute("href", String.format("/labels/%s", name))));
        }
        return listItems;
    }


    @Override
    public String plainOutput() {
        return model.toString();
    }
}
