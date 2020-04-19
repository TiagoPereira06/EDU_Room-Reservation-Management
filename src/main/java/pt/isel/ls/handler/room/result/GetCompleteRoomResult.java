package pt.isel.ls.handler.room.result;

import pt.isel.ls.handler.Result;

import java.util.LinkedList;
import java.util.List;

public class GetCompleteRoomResult extends Result {
    private final int labelNumber;

    public GetCompleteRoomResult(List<List<String>> value, int labelNumber) {
        super(value);
        this.labelNumber = labelNumber;
    }

    @Override
    public List<String> columns() {
        LinkedList<String> baseColumns = new LinkedList<>(List.of("Name", "Location", "Capacity", "Description"));
        for (int i = 0; i < labelNumber; i++) {
            baseColumns.add("Label");
        }
        return baseColumns;
    }

    @Override
    public String description() {
        return "GET Room";
    }
}
