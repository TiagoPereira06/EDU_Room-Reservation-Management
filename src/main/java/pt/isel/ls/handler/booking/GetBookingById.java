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

public class GetBookingById extends BookingHandler {

    @Override
    public GetBookingResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {

        String getBookingsByIdQuery = "SELECT * FROM bookings WHERE bid = ? ";
        PreparedStatement statement = connection.prepareStatement(getBookingsByIdQuery);
        statement.setInt(1, Integer.parseInt(commandRequest.getParametersByName(idArgument).get(0)));
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> bookingResult = new LinkedList<>();
        while (resultSet.next()) {
            bookingResult.add(
                    new Booking(
                            resultSet.getString("reservationowner"),
                            resultSet.getString("roomname"),
                            resultSet.getString("begintime"),
                            resultSet.getString("endtime")
                    ).parsePropertiesList()
            );
        }

        return new GetBookingResult(bookingResult);

    }

    @Override
    public String description() {
        return "Shows the detailed information for the bid booking";
    }
}
