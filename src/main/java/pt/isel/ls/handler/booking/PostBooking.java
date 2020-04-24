package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.booking.result.PostBookingResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.utils.UtilMethods.formatStringToDate;


public class PostBooking extends BookingHandler {

    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {
        String postBookingsQuery = "INSERT INTO bookings(reservationOwner, roomName, beginTime, endTime)"
                + "VALUES (?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(postBookingsQuery);
        String duration;
        String begin;
        String name;
        try {
            String owner = commandRequest.getParametersByName(ownerIdParameter).get(0);
            statement.setString(1, owner);
            name = commandRequest.getParametersByName(roomIdArgument).get(0);
            statement.setString(2, name);
            begin = commandRequest.getParametersByName(beginParameter).get(0);
            statement.setString(3, begin);
            duration = commandRequest.getParametersByName(durationParameter).get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new SQLException("Missing Arguments");
        }

        checkDuration(duration);
        Date beginTime = formatStringToDate(begin);
        //Adicionar ao beginTime o valor do duration e converter para string
        Date endTime = parseDuration(duration, beginTime);
        duration = Booking.dateFormat.format(endTime);
        statement.setString(4, duration);
        if (!checkIfRoomIsAvailable(connection, name, beginTime, endTime)) {
            throw new SQLException("ROOM IS NOT AVAILABLE");
        }
        statement.executeUpdate();
        List<List<String>> bookingResult = new LinkedList<>();
        bookingResult.add(Collections.singletonList(String.valueOf(getNextBookingId(connection))));
        return new PostBookingResult(bookingResult);
    }


    @Override
    public String description() {
        return "Creates new booking";
    }
}
