package pt.isel.ls.handler.option;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.Model;
import pt.isel.ls.request.CommandRequest;

public class Option implements CommandHandler {
    @Override
    public Model execute(CommandRequest commandRequest) {
        return new Model();
    }

    @Override
    public String description() {
        return "Shows all available commands";
    }
}

