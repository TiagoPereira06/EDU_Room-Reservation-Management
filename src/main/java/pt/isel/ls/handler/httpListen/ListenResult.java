package pt.isel.ls.handler.httpListen;

import pt.isel.ls.handler.Result;

import java.util.List;

public class ListenResult extends Result {

    public ListenResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return List.of("MESSAGE");
    }

    @Override
    public String description() {
        return "App is now HTTP compatible";
    }
}
