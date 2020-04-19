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

public class GetBookingByOwner extends BookingHandler {

    @Override
    public GetBookingResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getBookingsByOwnerQuery = "SELECT * FROM bookings WHERE reservationOwner = ?";
        PreparedStatement statement = connection.prepareStatement(getBookingsByOwnerQuery);
        statement.setString(1, commandRequest.getParametersByName(ownerIdArgument).get(0).getValue());
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> bookingResult = new LinkedList<>();
        while (resultSet.next()) {
            bookingResult.add(
                    new Booking(
                            resultSet.getString("reservationOwner"),
                            resultSet.getString("roomName"),
                            resultSet.getString("beginTime"),
                            resultSet.getString("endTime")
                    ).parsePropertiesList()
            );
        }
        return new GetBookingResult(bookingResult);
    }

    @Override
    public String description() {
        return "Shows the list of all bookings owned by the uid user";
    }
}
