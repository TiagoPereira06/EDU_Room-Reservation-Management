package pt.isel.ls;

import pt.isel.ls.http.StatusCode;

import java.io.InvalidObjectException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;

public class AppError {
    //TODO: FIX ERROR MAPPING!
    public HashMap<Exception, StatusCode> map = new HashMap<>();

    public AppError() {
        map.put(new NoSuchMethodException(), StatusCode.MethodNotAllowed);
        map.put(new SQLException(), StatusCode.InternalServerError);
        map.put(new InvalidObjectException(""), StatusCode.NotFound);
        map.put(new ParseException("", 0), StatusCode.BadRequest);
        map.put(new InvalidObjectException(""), StatusCode.NotFound);
    }

    public int getStatusCode(Exception e) {
        return (map.get(e).codeValue());
    }
}
