package pt.isel.ls.errors.command;

import pt.isel.ls.handler.Result;

public class ServerException extends CommandException {
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Result result) {
        super(message, result);
    }

    public ServerException() {
        super("Impossible to Start Server");
    }
}
