package pt.isel.ls.errors.router;

import pt.isel.ls.errors.AppException;

public class RouterException extends AppException {

    public RouterException() {
        super("Request Not Found");
    }
}
