package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

public class Exit implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection) {
        System.exit(0);
        return null;
    }

    @Override
    public String description() {
        return "Exits program";
    }

}
