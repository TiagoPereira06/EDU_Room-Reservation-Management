package pt.isel.ls.handler.booking.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class GetBookingById extends BookingHandler {

    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {

        String getBookingsByIdQuery = "SELECT * FROM bookings WHERE bid = ? ";
        PreparedStatement statement = connection.prepareStatement(getBookingsByIdQuery);
        int roomId = Integer.parseInt(commandRequest.getParametersByName(idArgument).get(0));
        statement.setInt(1, roomId);
        ResultSet resultSet = statement.executeQuery();
        Booking idBookings;
        resultSet.next();
        idBookings = new Booking(
                resultSet.getInt("bid"),
                resultSet.getString("reservationowner"),
                resultSet.getString("roomname"),
                resultSet.getString("begintime"),
                resultSet.getString("endtime")
        );
        return new GetBookingByIdView(idBookings);
    }

    @Override
    public String description() {
        return "Shows the detailed information for the bid booking";
    }
}
