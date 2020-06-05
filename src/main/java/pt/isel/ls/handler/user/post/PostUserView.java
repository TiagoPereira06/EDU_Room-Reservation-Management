package pt.isel.ls.handler.user.post;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class PostUserView extends View {
    private String model;

    public PostUserView(String userEmail) {
        this.model = userEmail;
    }

    public PostUserView() {

    }

    @Override
    public String name() {
        return model + " user posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("user id: " + model))

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
