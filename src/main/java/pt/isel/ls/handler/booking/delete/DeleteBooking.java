package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBooking extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {
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
            return new DeleteBookingResult(bookingId);
        });
    }

    @Override
    public String description() {
        return "Deletes a specified booking";
    }
}
