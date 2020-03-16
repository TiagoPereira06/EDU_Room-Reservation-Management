package pt.isel.ls.handler.label;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Template;

public class GetRoomsByLabel implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public Template getTemplate() {
        return Template.where(Template.LABELS_LID_ROOMS);
    }

    @Override
    public String description() {
        return "Shows a list with all the rooms having label lid";
    }
}
