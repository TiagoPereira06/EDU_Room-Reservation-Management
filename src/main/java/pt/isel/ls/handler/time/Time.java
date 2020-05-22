package pt.isel.ls.handler.time;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.util.Date;

public class Time implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) {
        return new TimeView(Booking.dateFormat.format(new Date()));
    }

    @Override
    public String description() {
        return "Gets Current Time";
    }
}

