package pt.isel.ls.handler.booking.delete;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.MissingArgumentsException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.util.NoSuchElementException;

import static pt.isel.ls.utils.UtilMethods.checkValid;

public class DeleteBooking extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String deleteBooking = "DELETE FROM bookings "
                    + "Where bid = ?";
            PreparedStatement statement = connection.prepareStatement(deleteBooking);
            String bookingId;
            try {
                bookingId = commandRequest.getParameterByName(idArgument);
                checkValid(bookingId, idArgument);
                statement.setInt(1, Integer.parseInt(bookingId));

            } catch (NoSuchElementException exception) {
                String msg = "Missing " + exception.getMessage();
                throw new MissingArgumentsException(msg);
            }

            statement.executeUpdate();
            return new DeleteBookingResult(bookingId);
        });
    }

    @Override
    public String description() {
        return "Deletes a specified booking";
    }
}
