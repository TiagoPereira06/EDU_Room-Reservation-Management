package pt.isel.ls.handler.option;

import pt.isel.ls.App;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

public class Option implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) {
        return new OptionView(App.router.getRoutes());
    }

    @Override
    public String description() {
        return "Shows all available commands";
    }
}

