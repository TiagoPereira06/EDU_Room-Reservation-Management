package pt.isel.ls.handler;

import java.util.LinkedList;
import java.util.List;

public class CommandResult {
    private List<Object> result;

    public CommandResult() {
        result = new LinkedList<>();
    }

    public List<Object> getResult() {
        return result;
    }

    public void setResult(List<Object> result) {
        this.result = result;
    }
}
