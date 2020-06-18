package pt.isel.ls.errors.database;

import pt.isel.ls.errors.AppException;

public class InternalDataBaseException extends AppException {

    public InternalDataBaseException() {
        super("Database is Unreachable");
    }
}
