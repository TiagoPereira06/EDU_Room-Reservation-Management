package pt.isel.ls.handler.index;

import pt.isel.ls.handler.View;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class IndexView extends View {
    @Override
    public String name() {
        return "Group 6's Application";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name())),
                        h1(text("Welcome to " + name())).addAttribute("style", "text-align:center"),
                        nav(setNavBar()).addAttribute("style", "text-align:center")
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

}
