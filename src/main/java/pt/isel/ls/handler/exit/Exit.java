package pt.isel.ls.handler.exit;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;

public class Exit implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest) throws SQLException, ParseException {
        System.exit(0);
        return new ExitView();
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
