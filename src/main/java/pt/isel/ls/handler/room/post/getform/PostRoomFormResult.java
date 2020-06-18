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
    private final boolean error;

    public PostRoomFormResult(List<Label> availableLabels, PostParameters postParameters) {
        this.postParameters = postParameters;
        this.availableLabels = availableLabels;
        this.error = !postParameters.isValid();
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
                                div(addInput("Name", "text")),
                                div(addInput("Location", "text")),
                                div(addInput("Capacity", "number")),
                                div(addInput("Description", "text")),
                                div(
                                        getLabelsCheckBoxes()
                                ),
                                input().addAttribute("type", "submit").addAttribute("value", "Create!")
                        ).addAttribute("method", "post")
                                .addAttribute("action", "/rooms/create")
                )

        ).build();
    }

    private Node[] addInput(String label, String inputType) {
        List<Node> nodes = new ArrayList<>();
        // ERRO NESTE LABEL ? -> SIM = != NULL
        String errorMsg = postParameters.getErrorByParameterName(label.toLowerCase());
        if (errorMsg == null) {
            nodes.add(label(text(label + " ")).addAttribute("for", label.toLowerCase()));
        } else {
            nodes.add(label(text(label.concat(" -> ").concat(errorMsg + " ")))
                    .addAttribute("for", label.toLowerCase())
                    .addAttribute("style", "color:red"));
        }
        Element input = input();
        input
                .addAttribute("type", inputType)
                .addAttribute("name", label.toLowerCase())
                .addAttribute("id", label.toLowerCase())
                .addAttribute("required", "true");

        if (error) {
            // COMO EXISTE ERRO, INPUT PREENCHIDO
            String value = postParameters.getParameterValue(label.toLowerCase());
            input.addAttribute("value", value);
        }
        nodes.add(input);
        nodes.add(br());
        return nodes.toArray(new Node[0]);
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
