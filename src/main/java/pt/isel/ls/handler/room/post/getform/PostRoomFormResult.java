package pt.isel.ls.handler.room.post.getform;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.PostParameters;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostRoomFormResult extends Result {
    private final List<Label> availableLabels;
    private final PostParameters postParameters;

    public PostRoomFormResult(List<Label> availableLabels, PostParameters postParameters) {
        this.postParameters = postParameters;
        this.availableLabels = availableLabels;
    }


    @Override
    public String name() {
        return "Room Creator";
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
                                div(addPostInput("Name", "text", postParameters)),
                                div(addPostInput("Location", "text", postParameters)),
                                div(addPostInput("Capacity", "number", postParameters)),
                                div(addPostInput("Description", "text", postParameters)),
                                div(
                                        getLabelsCheckBoxes()
                                ),
                                input().addAttribute("type", "submit").addAttribute("value", "Create!")
                        ).addAttribute("method", "post")
                                .addAttribute("action", "/rooms/create")
                )

        ).build();
    }


    private Node setNavBar() {
        return homeButton();
    }

    private Node[] getLabelsCheckBoxes() {
        List<Node> nodes = new ArrayList<>();
        String errorMsg = postParameters.getErrorByParameterName("label");
        if (errorMsg == null) {
            nodes.add(text("Available Labels: "));
            nodes.add(br());

        } else {
            nodes.add(paragraph(text("Available Labels: ".concat(" -> ").concat(errorMsg)))
                    .addAttribute("style", "color:red"));
        }
        for (Label l : availableLabels) {
            Element input = input();
            input.addAttribute("type", "checkbox")
                    .addAttribute("name", "label")
                    .addAttribute("id", "label")
                    .addAttribute("value", l.getName());

            List<String> labels = postParameters.getLabels();
            if (labels.contains(l.getName())) {
                input.addAttribute("", "checked");
            }
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
