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
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
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
        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return commandResult;

    }

    @Override
    public String description() {
        return "Shows the detailed information for the bid booking";
    }
}
