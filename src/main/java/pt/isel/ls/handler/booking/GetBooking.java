package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetBooking extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        CommandResult commandResult = new CommandResult();
        String getBookingsQuery = "SELECT * FROM bookings";
        PreparedStatement statement = connection.prepareStatement(getBookingsQuery);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            commandResult.getResult().add(
                    new Booking(resultSet.getString("reservationOwner"), resultSet.getString("roomName"),
                            resultSet.getString("beginTime"), resultSet.getString("endTime")));
        }
        return commandResult;
    }

    @Override
    public String description() {
        return "Show all bookings";
    }
}
