package pt.isel.ls.handler.option;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Element;
import pt.isel.ls.handler.result.html.Node;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class OptionView extends View {

    private List<List<String>> model;

    public OptionView(List<List<String>> routes) {
        this.model = routes;
    }

    public OptionView() {

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

    @Override
    public void setModel(Model resultModel) {
        this.model = (List<List<String>>) resultModel.getPrimaryData();
    }
}
