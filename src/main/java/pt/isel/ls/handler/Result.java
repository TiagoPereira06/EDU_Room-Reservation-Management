package pt.isel.ls.handler;

import pt.isel.ls.http.StatusCode;
import pt.isel.ls.request.PostParameters;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.ArrayList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public abstract class Result implements CommandResult {

    public Element homeButton() {
        return (anchor(text("Home")).addAttribute("href", "/"));
    }

    public Element button(String value, String link) {
        return (anchor(text(value)).addAttribute("href", link));
    }

    public Node[] addPostInput(String tag, String inputType, PostParameters postParameters) {
        List<Node> nodes = new ArrayList<>();
        // ERRO NESTE LABEL ? -> SIM = != NULL
        String errorMsg = postParameters.getErrorByParameterName(tag.toLowerCase());
        if (errorMsg == null) {
            nodes.add(label(text(tag + " ")).addAttribute("for", tag.toLowerCase()));
        } else {
            nodes.add(label(text(tag.concat(" -> ").concat(errorMsg + " ")))
                    .addAttribute("for", tag.toLowerCase())
                    .addAttribute("style", "color:red"));
        }
        Element input = input()
                .addAttribute("type", inputType)
                .addAttribute("name", tag.toLowerCase())
                .addAttribute("id", tag.toLowerCase())
                .addAttribute("required", "true");
        if (inputType.equals("number") && postParameters.isValid()) {
            input.addAttribute("value min", "1");
        }
        if (tag.equals("Duration")) {
            input.addAttribute("placeholder", "Minutes");
        }

        if (!postParameters.isValid()) {
            // COMO EXISTE ERRO, INPUT PREENCHIDO
            String value = postParameters.getParameterValue(tag.toLowerCase());
            if (inputType.equals("datetime-local")) {
                value = value.replace(" ", "T");
            }
            input.addAttribute("value", value);
        }
        nodes.add(input);
        nodes.add(br());
        return nodes.toArray(new Node[0]);
    }

    public Node[] addInput(String tag, String inputType) {
        List<Node> nodes = new ArrayList<>();

        nodes.add(label(text(tag + " ")).addAttribute("for", tag.toLowerCase()));
        Element input = input()
                .addAttribute("type", inputType)
                .addAttribute("name", tag.toLowerCase())
                .addAttribute("id", tag.toLowerCase());
        if (inputType.equals("number")) {
            input.addAttribute("value min", "1");
        }
        if (tag.equals("Duration")) {
            input.addAttribute("placeholder", "Minutes");
        }
        nodes.add(input);
        nodes.add(br());
        return nodes.toArray(new Node[0]);
    }

    @Override
    public StatusCode successStatusCode() {
        return StatusCode.Ok;
    }

    @Override
    public ResponseHeader responseHeader() {
        return null;
    }
}
