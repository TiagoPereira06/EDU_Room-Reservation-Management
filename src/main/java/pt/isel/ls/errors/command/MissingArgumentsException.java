package pt.isel.ls.errors.command;

import pt.isel.ls.handler.Result;

public class MissingArgumentsException extends CommandException {

    public MissingArgumentsException(Result result) {
        super("Missing Arguments", result);
    }

    public MissingArgumentsException() {
        super("Invalid Arguments");
    }

    public MissingArgumentsException(String msg, Result result) {
        super(msg, result);
    }

    public MissingArgumentsException(String message) {
        super(message);
    }
}
