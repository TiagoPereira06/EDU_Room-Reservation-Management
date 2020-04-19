package pt.isel.ls.handler.time;

import pt.isel.ls.handler.Result;

import java.util.Collections;
import java.util.List;

class TimeResult extends Result {
    public TimeResult(List<List<String>> value) {
        super(value);
    }

    @Override
    public List<String> columns() {
        return Collections.singletonList("Current Time");
    }

    @Override
    public String description() {
        return "GET Time";
    }
}
