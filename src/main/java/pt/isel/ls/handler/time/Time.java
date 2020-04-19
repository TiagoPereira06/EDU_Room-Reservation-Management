package pt.isel.ls.handler.time;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Time implements CommandHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) {
        List<List<String>> timeResult = new LinkedList<>();
        timeResult.add(Collections.singletonList(Booking.dateFormat.format(new Date())));
        return new TimeResult(timeResult);
    }

    @Override
    public String description() {
        return "Gets Current Time";
    }
}

