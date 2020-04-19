package pt.isel.ls.handler.option;

import pt.isel.ls.handler.Result;

import java.util.List;

class OptionResult extends Result {
    public OptionResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return List.of("Route", "Description");
    }

    @Override
    public String description() {
        return "GET All Routes";
    }
}
