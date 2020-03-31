package pt.isel.ls.handler.booking;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookingByOwner extends BookingHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getBookingsByOwnerQuery = "SELECT * FROM bookings WHERE reservationOwner = ?";
            PreparedStatement statement = connection.prepareStatement(getBookingsByOwnerQuery);
            statement.setString(1, commandRequest.getParametersByName(ownerIdArgument).get(0).getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commandResult.getResult().add(
                        new Booking(
                                resultSet.getString("reservationOwner"),
                                resultSet.getString("roomName"),
                                resultSet.getString("beginTime"),
                                resultSet.getString("endTime")
                        )
                );
            }
        } catch (SQLException e) {
            e.getMessage();
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
        return "Shows the list of all bookings owned by the uid user";
    }
}
