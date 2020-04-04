package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;


public interface CommandHandler {

    CommandResult execute(CommandRequest commandRequest, Connection connection) throws SQLException, ParseException;

    String description();
}
