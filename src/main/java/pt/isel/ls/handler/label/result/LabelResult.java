package pt.isel.ls.handler.label.result;

import pt.isel.ls.handler.Result;

import java.util.List;

public class LabelResult extends Result {
    private final String type;

    public LabelResult(List<List<String>> value, String type) {
        super(value);
        this.type = type;
    }

    @Override
    public List<String> columns() {
        return List.of("Name");
    }

    @Override
    public String description() {
        return type.concat(" Label");
    }
}
