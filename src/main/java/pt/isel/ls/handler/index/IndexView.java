package pt.isel.ls.handler.index;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class IndexView extends View {
    @Override
    public String name() {
        return "Group's 06 Application";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name())),
                        h1(text("Welcome to " + name())),
                        nav(setNavBar())
                )
        ).build();
    }

    private Node[] setNavBar() {
        List<Node> navItems = new ArrayList<>();
        navItems.add(button("Current Time", "/time"));
        navItems.add(text(" | "));
        navItems.add(button("Get Users", "/users"));
        navItems.add(text(" | "));
        navItems.add(button("Rooms Search", "/rooms/search"));
        navItems.add(text(" | "));
        navItems.add(button("Get Labels", "/labels"));
        return navItems.toArray(new Node[0]);
    }


    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }

    @Override
    public void setModel(Model resultModel) {

    }
}
