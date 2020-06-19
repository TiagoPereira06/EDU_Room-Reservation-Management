package pt.isel.ls.handler.booking.put;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.ConflictArgumentException;
import pt.isel.ls.errors.command.MissingArgumentsException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.NoSuchElementException;

import static pt.isel.ls.utils.UtilMethods.*;

public class PutBooking extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String putBookingQuery = "UPDATE bookings "
                    + "SET begintime = ?, endtime = ?, reservationowner = ? "
                    + "Where bid = ?";
            PreparedStatement statement = connection.prepareStatement(putBookingQuery);
            String duration;
            String beginTime;
            String roomName;
            String bookingId;
            try {
                beginTime = commandRequest.getParameterByName(beginParameter);
                checkValid(beginTime, beginParameter);
                statement.setString(1, beginTime);

                String reservationOwner = commandRequest.getParameterByName(ownerIdParameter);
                checkValid(reservationOwner, ownerIdParameter);
                statement.setString(3, reservationOwner);

                bookingId = commandRequest.getParameterByName(idArgument);
                checkValid(bookingId, idArgument);
                statement.setInt(4, Integer.parseInt(bookingId));

                duration = commandRequest.getParameterByName(durationParameter);
                checkValid(duration, durationParameter);
                checkIfDurationIsValid(duration);
                roomName = commandRequest.getParameterByName(roomIdArgument);
                checkValid(roomName, roomIdArgument);

            } catch (NoSuchElementException exception) {
                String msg = "Missing " + exception.getMessage();
                throw new MissingArgumentsException(msg);
            }

            Date dateBeginTime = formatStringToDate(beginTime);
            Date dateEndTime = parseDuration(duration, dateBeginTime);
            statement.setString(2, formatDateToString(dateEndTime));
            if (!checkIfRoomIsAvailable(connection, roomName, dateBeginTime, dateEndTime)) {
                throw new ConflictArgumentException("Room is not Available");
            }
            statement.executeUpdate();
            return new PutBookingResult(bookingId);
        });
    }


    @Override
    public String description() {
        return "Changes booking details";
    }
}
