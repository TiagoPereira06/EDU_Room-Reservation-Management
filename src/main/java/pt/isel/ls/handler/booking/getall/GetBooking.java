package pt.isel.ls.handler.booking.getall;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;


public class GetBooking extends BookingHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {
        String getBookingsQuery = "SELECT * FROM bookings";
        PreparedStatement statement = connection.prepareStatement(getBookingsQuery);
        ResultSet resultSet = statement.executeQuery();
        List<Booking> getAllBookingResult = new LinkedList<>();
        while (resultSet.next()) {
            Booking b = new Booking(
                    resultSet.getInt("bid"),
                    resultSet.getString("reservationOwner"),
                    resultSet.getString("roomName"),
                    resultSet.getString("beginTime"),
                    resultSet.getString("endTime"));
            getAllBookingResult.add(b);
        }
        return new GetBookingView(getAllBookingResult);
    }


    @Override
    public String description() {
        return "Show all bookings";
    }
}
