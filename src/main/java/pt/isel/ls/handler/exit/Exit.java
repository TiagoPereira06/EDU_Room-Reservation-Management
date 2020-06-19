package pt.isel.ls.handler.exit;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

public class Exit implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        System.exit(0);
        return new ExitResult();
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
