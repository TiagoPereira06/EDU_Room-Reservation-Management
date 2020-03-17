package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

public class Exit implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
