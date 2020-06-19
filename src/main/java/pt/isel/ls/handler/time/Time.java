package pt.isel.ls.handler.time;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.util.Date;

public class Time implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return new TimeResult(Booking.dateFormat.format(new Date()));
    }

    @Override
    public String description() {
        return "Gets Current Time";
    }
}

