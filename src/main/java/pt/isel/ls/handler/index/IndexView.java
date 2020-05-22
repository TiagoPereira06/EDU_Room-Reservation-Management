package pt.isel.ls.handler.index;

import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class IndexView extends View {
    @Override
    public String name() {
        return "home";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        button("Current Time", "/time"),
                        button("Get Users", "/users"),
                        button("Rooms Search", "/rooms/search"),
                        button("Get Labels", "/labels")
                )
        ).build();
    }


    @Override
    public String plainOutput() {
        return null;
    }
}
