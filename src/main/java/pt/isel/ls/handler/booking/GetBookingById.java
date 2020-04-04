package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookingById extends BookingHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection commandConnection) throws SQLException {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;


        String getBookingsByIdQuery = "SELECT * FROM bookings WHERE bid = ? ";
        PreparedStatement statement = connection.prepareStatement(getBookingsByIdQuery);
        statement.setInt(1, Integer.parseInt(commandRequest.getParametersByName(idArgument).get(0).getValue()));
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            commandResult.getResult().add(
                    new Booking(
                            resultSet.getString("reservationowner"),
                            resultSet.getString("roomname"),
                            resultSet.getString("begintime"),
                            resultSet.getString("endtime")
                    )
            );
        }

        return commandResult;

    }

    @Override
    public String description() {
        return "Shows the detailed information for the bid booking";
    }
}
