package pt.isel.ls.handler.option;

import pt.isel.ls.handler.View;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Element;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class OptionView extends View {

    private final List<List<String>> model;

    public OptionView(List<List<String>> routes) {
        this.model = routes;
    }

    @Override
    public String name() {
        return "GET All Routes";
    }

    public String htmlOutput() {
        return html(
                head(
                        nav(setNavBar()),
                        title(text(name()))
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
                        th(text("Route")),
                        th(text("Description"))
                ),
                tableData()
        ).addAttribute("border", "1");

    }

    private LinkedList<Node> tableData() {
        LinkedList<Node> list = new LinkedList<>();
        for (List<String> commands : model) {
            String command = commands.get(0);
            String desc = commands.get(1);
            list.add(
                    tr(
                            td((text(command))),
                            td((text(desc)))
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
