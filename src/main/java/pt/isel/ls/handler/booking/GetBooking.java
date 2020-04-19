package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.booking.result.GetBookingResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class GetBooking extends BookingHandler {
    @Override
    public GetBookingResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getBookingsQuery = "SELECT * FROM bookings";
        PreparedStatement statement = connection.prepareStatement(getBookingsQuery);
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> bookingResult = new LinkedList<>();
        while (resultSet.next()) {
            bookingResult.add(
                    new Booking(resultSet.getString("reservationOwner"), resultSet.getString("roomName"),
                            resultSet.getString("beginTime"), resultSet.getString("endTime")).parsePropertiesList());
        }
        return new GetBookingResult(bookingResult);
    }

    @Override
    public String description() {
        return "Show all bookings";
    }
}
