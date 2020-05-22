package pt.isel.ls.handler.user.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.model.User;

public class GetUserByIdView implements ResultView {
    private final User model;

    public GetUserByIdView(User userById) {
        this.model = userById;
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
