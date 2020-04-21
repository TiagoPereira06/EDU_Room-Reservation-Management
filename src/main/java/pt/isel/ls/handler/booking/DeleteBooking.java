package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.booking.result.PostBookingResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DeleteBooking extends BookingHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String deleteBooking = "DELETE FROM bookings "
                + "Where bid = ?";
        PreparedStatement statement = connection.prepareStatement(deleteBooking);
        String bookingId = commandRequest.getParametersByName(idArgument).get(0);
        statement.setInt(1, Integer.parseInt(bookingId));
        statement.executeUpdate();
        List<List<String>> bookingResult = new LinkedList<>();
        bookingResult.add(Collections.singletonList(bookingId));
        return new PostBookingResult(bookingResult);
    }

    @Override
    public String description() {
        return "Deletes a specified booking";
    }
}
