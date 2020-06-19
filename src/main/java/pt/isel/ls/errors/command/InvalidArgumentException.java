package pt.isel.ls.errors.command;

import pt.isel.ls.handler.Result;

public class InvalidArgumentException extends CommandException {


    public InvalidArgumentException(Result formResult) {
        super("Invalid Argument", formResult);
    }

    public InvalidArgumentException(String msg, Result formResult) {
        super(msg, formResult);
    }


    public InvalidArgumentException() {
        super("Invalid Argument");
    }

    public InvalidArgumentException(String msg) {
        super(msg);
    }
}
