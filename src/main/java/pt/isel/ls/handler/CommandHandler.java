package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;


public interface CommandHandler {

    ResultInterface execute(CommandRequest commandRequest, Connection connection) throws Exception;

    String description();

}
