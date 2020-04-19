package pt.isel.ls.handler.room.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class PostRoomResult extends Result {
    public PostRoomResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return List.of("Name");
    }

    @Override
    public String description() {
        return "POST Room";
    }
}
