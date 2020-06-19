package pt.isel.ls.errors.command;

import pt.isel.ls.errors.AppException;
import pt.isel.ls.handler.Result;

public class CommandException extends AppException {

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Result result) {
        super(message, result);
    }


}
