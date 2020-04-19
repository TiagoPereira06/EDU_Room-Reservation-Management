package pt.isel.ls.handler.user.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class PostUserResult extends Result {
    public PostUserResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return List.of("Email");
    }

    @Override
    public String description() {
        return "POST User";
    }
}
