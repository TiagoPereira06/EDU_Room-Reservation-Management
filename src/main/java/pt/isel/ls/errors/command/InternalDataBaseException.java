package pt.isel.ls.errors.command;


public class InternalDataBaseException extends CommandException {

    public InternalDataBaseException() {
        super("Database Connection is Unstable");
    }

    public InternalDataBaseException(String message) {
        super(message);
    }
}
