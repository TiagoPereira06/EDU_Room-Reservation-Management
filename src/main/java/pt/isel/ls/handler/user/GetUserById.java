package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

public class GetUserById implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public String description() {
        return "Show users with id";
    }


}
