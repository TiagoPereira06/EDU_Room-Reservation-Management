package pt.isel.ls.handler.label.getbyid;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetLabelByIdView extends View {
    private Label model;
    private List<Room> rooms;

    public GetLabelByIdView(Label labelById, List<Room> roomsResult) {
        this.model = labelById;
        this.rooms = roomsResult;
    }

    public GetLabelByIdView() {

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
        if (rooms.size() != 0) {
            listItems.add(li(bold((text("Room(s) Compatible:")))));
            for (Room m : rooms) {
                String name = m.getName();
                listItems.add(dd(anchor(text(name)).addAttribute("href", String.format("/rooms/%s", name))));

            }
        }
        return listItems;
    }


    @Override
    public String plainOutput() {
        return model.toString();
    }

    @Override
    public void setModel(Model resultModel) {
        this.model = (Label) resultModel.getPrimaryData();
        this.rooms = (List<Room>) resultModel.getSecondaryData();
    }
}
