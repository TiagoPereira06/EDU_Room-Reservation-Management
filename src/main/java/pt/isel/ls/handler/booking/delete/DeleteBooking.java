package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBooking extends BookingHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String deleteBooking = "DELETE FROM bookings "
                + "Where bid = ?";
        PreparedStatement statement = connection.prepareStatement(deleteBooking);
        String bookingId;
        try {
            bookingId = commandRequest.getParametersByName(idArgument).get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new SQLException("Missing Arguments");
        }
        statement.setInt(1, Integer.parseInt(bookingId));
        statement.executeUpdate();
        return new DeleteBookingView(bookingId);
    }

    @Override
    public String description() {
        return "Deletes a specified booking";
    }
}
