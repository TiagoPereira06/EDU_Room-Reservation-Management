package pt.isel.ls.handler.option;

import pt.isel.ls.App;
import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

public class Option implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return new OptionResult(App.commandRouter.getRoutes());
    }

    @Override
    public String description() {
        return "Shows all available commands";
    }
}

