package pt.isel.ls.errors;

import pt.isel.ls.errors.command.*;
import pt.isel.ls.http.StatusCode;

import java.text.ParseException;

public class AppError {

    public static int getStatusCode(Exception e) {
        StatusCode code;
        final Class<? extends Exception> exClass = e.getClass();
        if (exClass.equals(InternalDataBaseException.class) || exClass.equals(ServerException.class)) {
            code = StatusCode.InternalServerError;
        } else if (exClass.equals(ParseException.class) || exClass.equals(MissingArgumentsException.class)
                || exClass.equals(InvalidArgumentException.class)) {
            code = StatusCode.BadRequest;
        } else if (exClass.equals(ConflictArgumentException.class)) {
            code = StatusCode.Conflict;
        } else {
            code = StatusCode.NotFound;
        }
        return code.getCodeValue();
    }
}
