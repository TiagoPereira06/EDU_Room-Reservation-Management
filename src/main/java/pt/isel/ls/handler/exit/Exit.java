package pt.isel.ls.handler.exit;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;

public class Exit implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws SQLException, ParseException {
        System.exit(0);
        return new ExitResult();
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
