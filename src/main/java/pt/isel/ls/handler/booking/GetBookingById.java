package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

public class GetBookingById implements CommandHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public String description() {
        return "Shows the detailed information for the bid booking";
    }
}
