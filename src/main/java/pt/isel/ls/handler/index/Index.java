package pt.isel.ls.handler.index;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;

public class Index implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws SQLException, ParseException {
        return new IndexResult();
    }

    @Override
    public String description() {
        return "Index of Server Application";
    }
}
