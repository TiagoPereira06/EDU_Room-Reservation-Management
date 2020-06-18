package pt.isel.ls.handler.label.getall;

import pt.isel.ls.handler.Result;
import pt.isel.ls.model.Label;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class GetLabelResult extends Result {
    private final List<Label> model;

    public GetLabelResult(List<Label> allLabels) {
        this.model = allLabels;
    }


    @Override
    public String name() {
        return "All Labels";
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
                        setTable()
                )
        ).build();
    }

    private Node setNavBar() {
        return homeButton();
    }


    private Element setTable() {
        return Element.table(
                tr(
                        th(text("Name"))
                ),
                tableData()
        ).addAttribute("border", "1");

    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (Label label : model) {
            String name = label.getName();
            list.add(
                    tr(
                            td(anchor(text(name)).addAttribute("href", String.format("/labels/%s", name)))
                    )
            );
        }
        return list;
    }

    @Override
    public String plainOutput() {
        return model.toString();
    }

}
