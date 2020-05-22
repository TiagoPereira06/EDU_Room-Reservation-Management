package pt.isel.ls.handler.time;

import pt.isel.ls.handler.ResultView;

import static pt.isel.ls.handler.result.html.Element.*;

class TimeView implements ResultView {

    private final String model;

    public TimeView(String currentTime) {
        this.model = currentTime;
    }

    @Override
    public String name() {
        return "GET Time";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        h2(text(model)),
                        h3(anchor(text("Home")).addAttribute("href","/"))
                )
        ).build();
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
