package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static pt.isel.ls.utils.UtilMethods.formatStringToDate;


public class PostBooking extends BookingHandler {

    @Override
    public ResultView execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {
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
            return new PostBookingView(String.valueOf(getNextBookingId(connection)));
        });
    }


    @Override
    public String description() {
        return "Creates new booking";
    }
}
