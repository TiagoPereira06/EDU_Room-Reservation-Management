package pt.isel.ls.handler.index;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

public class Index implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return new IndexResult();
    }

    @Override
    public String description() {
        return "Index of Server Application";
    }
}
