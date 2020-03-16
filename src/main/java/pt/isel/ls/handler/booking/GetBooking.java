package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Template;

public class GetBooking implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public Template getTemplate() {
        return Template.where(Template.ROOMS_RID_BOOKINGS);
    }

    @Override
    public String description() {
        return "Show all bookings";
    }
}
