package pt.isel.ls.handler.booking.put;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static pt.isel.ls.utils.UtilMethods.formatDateToString;
import static pt.isel.ls.utils.UtilMethods.formatStringToDate;

public class PutBooking extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws Exception {
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
                beginTime = getBeginTime(commandRequest);
                statement.setString(1, beginTime);

                String reservationOwner = commandRequest.getParametersByName(ownerIdParameter).get(0);
                statement.setString(3, reservationOwner);

                bookingId = commandRequest.getParametersByName(idArgument).get(0);
                statement.setInt(4, Integer.parseInt(bookingId));

                duration = getDuration(commandRequest);

                checkDuration(duration);

                roomName = commandRequest.getParametersByName(roomIdArgument).get(0);

            } catch (IndexOutOfBoundsException exception) {
                throw new SQLException("Missing Arguments");
            }
            Date dateBeginTime = formatStringToDate(beginTime);
            Date dateEndTime = parseDuration(duration, dateBeginTime);
            statement.setString(2, formatDateToString(dateEndTime));
            if (!checkIfRoomIsAvailable(connection, roomName, dateBeginTime, dateEndTime)) {
                throw new SQLException("ROOM IS NOT AVAILABLE");
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
