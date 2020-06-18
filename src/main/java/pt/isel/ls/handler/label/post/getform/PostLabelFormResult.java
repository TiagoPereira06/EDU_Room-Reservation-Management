package pt.isel.ls.handler.label.post.getform;

import pt.isel.ls.handler.Result;
import pt.isel.ls.request.PostParameters;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostLabelFormResult extends Result {
    private final PostParameters postParameters;
    private final boolean error;

    public PostLabelFormResult(PostParameters postParameters) {
        this.postParameters = postParameters;
        this.error = !postParameters.isValid();
    }


    @Override
    public String name() {
        return "Label Creator";
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
                                input().addAttribute("type", "submit").addAttribute("value", "Create!")

                        ).addAttribute("method", "post")
                                .addAttribute("action", "/labels/create")
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


    @Override
    public String plainOutput() {
        return "Only Available on HTML Support";
    }
}
