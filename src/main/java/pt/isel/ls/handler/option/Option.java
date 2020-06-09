package pt.isel.ls.handler.option;

import pt.isel.ls.App;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.CommandRequest;

public class Option implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest) {
        return new OptionView(App.commandRouter.getRoutes());
    }

    @Override
    public String description() {
        return "Shows all available commands";
    }
}

