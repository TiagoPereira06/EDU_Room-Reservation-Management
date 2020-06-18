package pt.isel.ls.errors.handler;

import pt.isel.ls.errors.AppException;
import pt.isel.ls.handler.Result;

public class HandlerException extends AppException {

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(String message, Result result) {
        super(message, result);
    }


}
