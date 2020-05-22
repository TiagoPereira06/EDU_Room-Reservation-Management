package pt.isel.ls.handler.exit;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

public class Exit implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) {
        System.exit(0);
        return null;
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
