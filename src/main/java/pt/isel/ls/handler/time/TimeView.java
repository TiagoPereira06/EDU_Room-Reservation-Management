package pt.isel.ls.handler.time;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.result.html.Node;

import static pt.isel.ls.handler.result.html.Element.*;

public class TimeView extends View {

    private String model;

    public TimeView(String currentTime) {
        this.model = currentTime;
    }

    public TimeView() {

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

    @Override
    public void setModel(Model resultModel) {
        this.model = (String) resultModel.getPrimaryData();
    }
}
