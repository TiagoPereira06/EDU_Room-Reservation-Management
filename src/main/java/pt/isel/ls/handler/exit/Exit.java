package pt.isel.ls.handler.exit;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.Model;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;

public class Exit implements CommandHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws SQLException, ParseException {
        System.exit(0);
        return null;
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
