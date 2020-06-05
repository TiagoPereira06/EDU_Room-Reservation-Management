package pt.isel.ls.handler.time;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.Model;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class Time implements CommandHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws SQLException, ParseException {
        return new Model(Booking.dateFormat.format(new Date()));
    }

    @Override
    public String description() {
        return "Gets Current Time";
    }
}

