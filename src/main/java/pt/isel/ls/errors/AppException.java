package pt.isel.ls.errors;

import pt.isel.ls.handler.Result;

public class AppException extends Exception {
    public final Result result;

    public AppException(String message) {
        super(message);
        this.result = null;
    }

    public AppException(String message, Result result) {
        super(message);
        this.result = result;
    }
}
