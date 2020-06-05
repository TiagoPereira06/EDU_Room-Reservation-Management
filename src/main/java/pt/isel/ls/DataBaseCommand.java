package pt.isel.ls;

import pt.isel.ls.handler.Model;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

public interface DataBaseCommand {
    Model execute(Connection connection) throws SQLException, ParseException;
}
