package pt.isel.ls.errors.command;

import pt.isel.ls.handler.Result;

public class ResultNotFoundException extends CommandException {
    public ResultNotFoundException(String message) {
        super(message);
    }

    public ResultNotFoundException(String message, Result result) {
        super(message, result);
    }

    public ResultNotFoundException() {
        super("Result Not Found");
    }
}
