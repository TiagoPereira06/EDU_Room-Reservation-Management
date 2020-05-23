package pt.isel.ls.handler.label.post;

import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class PostLabelView extends View {
    private final String model;

    public PostLabelView(String postedLabelName) {
        this.model = postedLabelName;
    }

    @Override
    public String name() {
        return model + " label posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("label id: " + model))

        ).build();
    }

    @Override
    public String plainOutput() {
        return model;
    }
}
