package pt.isel.ls.handler.room.search;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Label;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class SearchRoomResult extends Result {
    private final List<Label> model;

    public SearchRoomResult(List<Label> model) {
        this.model = model;
    }

    @Override
    public String name() {
        return "Rooms Search Engine";
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
                        form(
                                div(addInput("Capacity", "number")),
                                div(addInput("Begin", "datetime-local")),
                                div(addInput("Duration", "number")),
                                div(
                                        getLabelsCheckBoxes()
                                ),
                                input().addAttribute("type", "submit").addAttribute("value", "Search!")

                        ).addAttribute("method", "get")
                                .addAttribute("action", "/rooms")
                )

        ).build();
    }

    private Node setNavBar() {
        return homeButton();
    }

    private Node[] getLabelsCheckBoxes() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(text("Available Labels: "));
        for (Label l : model) {
            Node input = input()
                    .addAttribute("type", "checkbox")
                    .addAttribute("name", "label")
                    .addAttribute("id", "label")
                    .addAttribute("value", l.getName());
            Node label = label(text(l.getName())).addAttribute("for", "label");
            nodes.add(input);
            nodes.add(label);
        }
        nodes.add(br());
        return nodes.toArray(new Node[0]);
    }

    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }
}
