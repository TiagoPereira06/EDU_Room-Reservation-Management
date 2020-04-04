package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;


public class PostBooking extends BookingHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {

        final long oneMinuteInMillis = 60000;//millisecs
        final CommandResult commandResult = new CommandResult();

        String postBookingsQuery = "INSERT INTO bookings(reservationOwner, roomName, beginTime, endTime)"
                + "VALUES (?,?,?,?) ";
        PreparedStatement statement = connection.prepareStatement(postBookingsQuery);
        String owner = commandRequest.getParametersByName(ownerIdParameter).get(0).getValue();
        statement.setString(1, owner);
        String name = commandRequest.getParametersByName(roomIdParameter).get(0).getValue();
        statement.setString(2, name);
        String begin = commandRequest.getParametersByName(beginParameter).get(0).getValue().replace('+', ' ');
        statement.setString(3, begin);
        String duration = commandRequest.getParametersByName(durationParameter).get(0).getValue();
        if (Integer.parseInt(duration) < 10) {
            throw new SQLException("DURATION MUST BE LONGER !");
        }
        //Adicionar ao beginTime o valor do duration e converter para string
        Date beginTime = Booking.dateFormat.parse(begin);
        Date endTime = new Date(beginTime.getTime() + oneMinuteInMillis * Integer.parseInt(duration));
        duration = Booking.dateFormat.format(endTime);
        statement.setString(4, duration);
        if (!checkIfRoomIsAvailable(connection, name, beginTime, endTime)) {
            throw new SQLException("ROOM IS NOT AVAILABLE !");
        }
        statement.executeUpdate();
        commandResult.getResult().add("BOOKING INSERTED : BOOKING INFO -> "
                .concat(String.valueOf(getNextBookingId(connection))));

        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new booking";
    }
}
