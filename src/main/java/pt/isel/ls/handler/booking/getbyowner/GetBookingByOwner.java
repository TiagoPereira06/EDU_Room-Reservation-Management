package pt.isel.ls.handler.booking.getbyowner;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class GetBookingByOwner extends BookingHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String getBookingsByOwnerQuery = "SELECT * FROM bookings WHERE reservationOwner = ?";
            PreparedStatement statement = connection.prepareStatement(getBookingsByOwnerQuery);
            statement.setString(1, commandRequest.getParameterByName(ownerIdArgument));
            ResultSet resultSet = statement.executeQuery();
            List<Booking> ownerBookings = new LinkedList<>();
            while (resultSet.next()) {
                Booking b = new Booking(
                        resultSet.getInt("bid"),
                        resultSet.getString("reservationOwner"),
                        resultSet.getString("roomName"),
                        resultSet.getString("beginTime"),
                        resultSet.getString("endTime"));
                ownerBookings.add(b);
            }
            return new GetBookingByOwnerResult(ownerBookings);
        });
    }

    @Override
    public String description() {
        return "Shows the list of all bookings owned by the uid user";
    }
}
