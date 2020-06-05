package pt.isel.ls;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.result.View;

public class CommandPackage {
    public CommandHandler handler;
    public View view;

    public CommandPackage(CommandHandler handler, View view) {
        this.handler = handler;
        this.view = view;
    }
}
