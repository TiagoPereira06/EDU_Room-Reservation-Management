package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.booking.result.PostBookingResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.utils.UtilMethods.formatDateToString;
import static pt.isel.ls.utils.UtilMethods.formatStringToDate;

public class PutBooking extends BookingHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {
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
        List<List<String>> bookingResult = new LinkedList<>();
        bookingResult.add(Collections.singletonList(bookingId));
        return new PostBookingResult(bookingResult);
    }


    @Override
    public String description() {
        return "Changes booking details";
    }
}
