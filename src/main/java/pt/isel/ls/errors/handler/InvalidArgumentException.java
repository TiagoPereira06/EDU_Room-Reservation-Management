package pt.isel.ls.errors.handler;

import pt.isel.ls.handler.Result;

public class InvalidArgumentException extends HandlerException {


    public InvalidArgumentException(Result formResult) {
        super("Invalid Arguments", formResult);
    }

    public InvalidArgumentException(String msg, Result formResult) {
        super(msg, formResult);
    }


    public InvalidArgumentException() {
        super("Invalid Arguments");
    }
}
