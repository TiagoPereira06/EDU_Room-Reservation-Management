package pt.isel.ls.handler.room;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Template;

public class GetRoomById implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public Template getTemplate() {
        return Template.where(Template.ROOMS_RID);
    }

    @Override
    public String description() {
        return "Show the detailed information for the room identified by rid";
    }
}