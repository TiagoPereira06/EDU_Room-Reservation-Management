package pt.isel.ls.handler.label.post;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class PostLabelView extends View {
    private String model;

    public PostLabelView(String postedLabelName) {
        this.model = postedLabelName;
    }

    public PostLabelView() {

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

    @Override
    public void setModel(Model resultModel) {
        this.model = (String) resultModel.getPrimaryData();
    }
}
