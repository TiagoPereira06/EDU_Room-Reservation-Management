package pt.isel.ls.handler.time;

import pt.isel.ls.handler.Result;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.Node;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class TimeResult extends Result {

    private final String model;

    public TimeResult(String model) {
        this.model = model;
    }

    @Override
    public String name() {
        return "Current Time";
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
                        h2(text(model))

                )
        ).build();
    }

    private Node setNavBar() {
        return homeButton();
    }

    @Override
    public String plainOutput() {
        return model;
    }

}
