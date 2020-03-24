package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

public class Exit implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        System.exit(1);
        return null;
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
