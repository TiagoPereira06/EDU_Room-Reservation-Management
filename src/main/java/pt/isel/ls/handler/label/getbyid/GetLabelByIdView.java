package pt.isel.ls.handler.label.getbyid;

import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Element;
import pt.isel.ls.handler.result.html.Node;
import pt.isel.ls.model.Label;

import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.handler.result.html.Element.*;

public class GetLabelByIdView extends View {
    private final Label model;

    public GetLabelByIdView(Label labelById) {
        this.model = labelById;
    }

    @Override
    public String name() {
        return model.getName() + " label details";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        Element.dl(listFormat()),
                        button("All Labels", "/labels"),
                        homeButton(),
                        button("Rooms", "/rooms%20label=" + model.getName())
                )
        ).build();
    }

    private List<Node> listFormat() {
        LinkedList<Node> listItems = new LinkedList<>();
        listItems.add(li(bold((text("Name:")))));
        listItems.add(dd(text(model.getName())));
        return listItems;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
