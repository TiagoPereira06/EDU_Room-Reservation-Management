package pt.isel.ls.handler.booking.post;

import pt.isel.ls.errors.command.*;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.handler.booking.post.getform.PostBookingFormResult;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.Date;
import java.util.NoSuchElementException;

import static pt.isel.ls.utils.UtilMethods.*;


public class PostBooking extends BookingHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String postBookingsQuery = "INSERT INTO bookings(reservationOwner, roomName, beginTime, endTime)"
                    + "VALUES (?,?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(postBookingsQuery);
            String duration;
            String begin;
            String roomName = "";
            String owner;
            try {
                owner = commandRequest.getParameterByName(ownerIdParameter);
                checkValid(owner, ownerIdParameter);
                statement.setString(1, owner);

                roomName = commandRequest.getParameterByName(roomIdArgument);
                checkValid(roomName, roomIdArgument);
                statement.setString(2, roomName);

                begin = commandRequest.getParameterByName(beginParameter);
                checkValid(begin, beginParameter);
                begin = formatStringDate(begin);
                statement.setString(3, begin);

                duration = commandRequest.getParameterByName(durationParameter);
                checkValid(duration, durationParameter);

            } catch (NoSuchElementException exception) {
                String tag = exception.getMessage();
                String msg = "Missing " + tag;
                commandRequest.getPostParameters().addErrorMsg(tag, msg);
                throw new MissingArgumentsException(
                        msg,
                        getPostBookingFormResult(commandRequest, connection, roomName)
                );
            }

            try {
                checkIfDurationIsValid(duration);
            } catch (InvalidArgumentException e) {
                String msg = e.getMessage();
                commandRequest.getPostParameters().addErrorMsg("duration", msg);
                throw new InvalidArgumentException(msg, getPostBookingFormResult(commandRequest, connection, roomName));
            }

            Date beginTime;

            try {
                beginTime = formatStringToDate(begin);
            } catch (ParseException e) {
                String msg = "Invalid date format";
                commandRequest.getPostParameters().addErrorMsg("begin", "Invalid date format");
                throw new InvalidArgumentException(msg, getPostBookingFormResult(commandRequest, connection, roomName));
            }
            //Adicionar ao beginTime o valor do duration e converter para string
            Date endTime = parseDuration(duration, beginTime);
            duration = Booking.dateFormat.format(endTime);
            statement.setString(4, duration);

            if (!checkIfRoomIsAvailable(connection, roomName, beginTime, endTime)) {
                String msg = "Room is not available";
                commandRequest.getPostParameters().addErrorMsg("begin", msg);
                throw new ConflictArgumentException(
                        msg,
                        getPostBookingFormResult(commandRequest, connection, roomName)
                );
            }
            statement.executeUpdate();
            final String id = String.valueOf(getNextBookingId(connection));
            return new PostBookingResult(
                    id,
                    String.format("/rooms/%s/bookings/%s", roomName, id)
            );
        });
    }

    private PostBookingFormResult getPostBookingFormResult(
            CommandRequest commandRequest,
            Connection connection,
            String roomName
    ) throws InternalDataBaseException {

        return new PostBookingFormResult(
                UserHandler.getAllUsers(connection),
                commandRequest.getPostParameters(),
                roomName
        );
    }

    @Override
    public String description() {
        return "Creates new booking";
    }
}
