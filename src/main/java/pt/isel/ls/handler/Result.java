package pt.isel.ls.handler;

import java.util.List;

public abstract class Result implements ResultInterface {
    public List<List<String>> value;

    public Result(List<List<String>> value) {
        this.value = value;
    }

    @Override
    public List<List<String>> values() {
        return value;
    }
}
