package pt.isel.ls.handler.room.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class GetRoomResult extends Result {
    public GetRoomResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return List.of("Name", "Location", "Capacity", "Description");
    }

    @Override
    public String description() {
        return "GET Room";
    }
}
