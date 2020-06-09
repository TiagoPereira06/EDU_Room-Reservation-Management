package pt.isel.ls.handler.user.post;

import pt.isel.ls.handler.View;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostUserView extends View {
    private final String model;

    public PostUserView(String userEmail) {
        this.model = userEmail;
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

}
