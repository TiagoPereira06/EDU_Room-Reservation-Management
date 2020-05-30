package pt.isel.ls;

import org.postgresql.util.PSQLException;
import pt.isel.ls.http.StatusCode;

import java.sql.SQLException;
import java.text.ParseException;

public class AppError {

    public static int getStatusCode(Exception e) {
        StatusCode code;
        if (e.getClass().equals(NoSuchMethodException.class)) {
            code = StatusCode.NotImplemented;
        } else if (
                e.getClass().equals(SQLException.class)
                        ||
                        e.getClass().equals(PSQLException.class)
        ) {
            code = StatusCode.InternalServerError;
        } else if (e.getClass().equals(ParseException.class)) {
            code = StatusCode.BadRequest;
        } else {
            code = StatusCode.NotFound;
        }
        return code.getCodeValue();
    }
}
