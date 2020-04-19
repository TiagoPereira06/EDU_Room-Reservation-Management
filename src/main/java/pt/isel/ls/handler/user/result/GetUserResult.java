package pt.isel.ls.handler.user.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class GetUserResult extends Result {
    public GetUserResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return List.of("Email", "Name");
    }

    @Override
    public String description() {
        return "GET User";
    }
}
