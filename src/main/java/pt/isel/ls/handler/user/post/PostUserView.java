package pt.isel.ls.handler.user.post;

import pt.isel.ls.handler.ResultView;

public class PostUserView implements ResultView {
    private final String model;

    public PostUserView(String userEmail) {
        this.model = userEmail;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String htmlOutput() {
        return null;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
