package pt.isel.ls;

import org.postgresql.util.PSQLException;
import pt.isel.ls.http.StatusCode;

import java.sql.SQLException;
import java.text.ParseException;

public class AppError {

    public static int getStatusCode(Exception e) {
        StatusCode code;
        final Class<? extends Exception> exClass = e.getClass();
        if (exClass.equals(NoSuchMethodException.class)) {
            code = StatusCode.NotImplemented;
        } else if (exClass.equals(SQLException.class) || exClass.equals(PSQLException.class)) {
            code = StatusCode.InternalServerError;
        } else if (exClass.equals(ParseException.class)) {
            code = StatusCode.BadRequest;
        } else {
            code = StatusCode.NotFound;
        }
        return code.getCodeValue();
    }
}
