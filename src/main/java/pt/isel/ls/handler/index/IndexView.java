package pt.isel.ls.handler.index;

import pt.isel.ls.handler.ResultView;

import static pt.isel.ls.handler.result.html.Element.*;

public class IndexView implements ResultView {
    @Override
    public String name() {
        return "Home";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        h2(anchor(text("Current Time")).addAttribute("href", "/time")),
                        h2(anchor(text("Get Users")).addAttribute("href", "/users")),
                        h2(anchor(text("Room Search")).addAttribute("href", "/rooms/search")),
                        h2(anchor(text("Get Labels")).addAttribute("href", "/labels"))
                )
        ).build();
    }


    @Override
    public String plainOutput() {
        return null;
    }
}
