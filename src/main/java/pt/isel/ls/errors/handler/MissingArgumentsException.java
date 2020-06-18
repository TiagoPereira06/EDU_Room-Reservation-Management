package pt.isel.ls.errors.handler;

import pt.isel.ls.handler.Result;

public class MissingArgumentsException extends HandlerException {

    public MissingArgumentsException(Result result) {
        super("Missing Arguments", result);
    }

    public MissingArgumentsException() {
        super("Invalid Arguments");
    }
}
