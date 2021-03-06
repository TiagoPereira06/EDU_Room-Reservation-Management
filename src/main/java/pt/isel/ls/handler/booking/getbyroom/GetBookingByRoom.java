package pt.isel.ls.handler.booking.getbyroom;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class GetBookingByRoom extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String getBookingsByRoomQuery = "SELECT * FROM bookings WHERE roomname = ?";
            PreparedStatement statement = connection.prepareStatement(getBookingsByRoomQuery);
            statement.setString(1, commandRequest.getParameterByName(roomIdArgument));
            ResultSet resultSet = statement.executeQuery();
            List<Booking> roomBookings = new LinkedList<>();
            while (resultSet.next()) {
                Booking b = new Booking(
                        resultSet.getInt("bid"),
                        resultSet.getString("reservationOwner"),
                        resultSet.getString("roomName"),
                        resultSet.getString("beginTime"),
                        resultSet.getString("endTime"));
                roomBookings.add(b);
            }
            return new GetBookingByRoomResult(roomBookings);
        });
    }

    @Override
    public String description() {
        return "Shows the list of all bookings at rid room";
    }
}
