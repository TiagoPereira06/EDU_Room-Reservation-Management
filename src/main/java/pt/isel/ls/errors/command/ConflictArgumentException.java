package pt.isel.ls.errors.command;

import pt.isel.ls.handler.Result;

public class ConflictArgumentException extends CommandException {
    public ConflictArgumentException(String message) {
        super(message);
    }

    public ConflictArgumentException(String message, Result result) {
        super(message, result);
    }

    public ConflictArgumentException(Result result) {
        super("Conflict Argument", result);
    }
}
