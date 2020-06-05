package pt.isel.ls.handler.room.search;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Label;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class SearchRoomView extends View {
    private List<Label> model;

    public SearchRoomView() {
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
                                div(
                                        label(text("Capacity ")).addAttribute("for", "capacity"),
                                        input()
                                                .addAttribute("type", "number")
                                                .addAttribute("name", "capacity")
                                                .addAttribute("id", "capacity")
                                ),
                                div(
                                        label(text("Begin Time ")).addAttribute("for", "begin"),
                                        input()
                                                .addAttribute("type", "datetime-local")
                                                .addAttribute("name", "begin")
                                                .addAttribute("id", "begin")
                                ),
                                div(
                                        label(text("Duration ")).addAttribute("for", "duration"),
                                        input()
                                                .addAttribute("type", "number")
                                                .addAttribute("name", "duration")
                                                .addAttribute("id", "Duration")
                                ),
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
        return nodes.toArray(new Node[0]);
    }

    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }

    @Override
    public void setModel(Model resultModel) {
        this.model = (List<Label>) resultModel.getPrimaryData();

    }
}
