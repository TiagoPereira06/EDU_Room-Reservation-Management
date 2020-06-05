package pt.isel.ls.handler.option;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.Model;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;

public class Option implements CommandHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws SQLException, ParseException {
        return new Model();
    }

    @Override
    public String description() {
        return "Shows all available commands";
    }
}

