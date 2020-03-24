package pt.isel.ls.handler.booking;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookingById implements CommandHandler {
    private final int uidPosition = 0;

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getBookingsByIdQuery = "SELECT * FROM bookings WHERE bid = ? ";
            PreparedStatement statement = connection.prepareStatement(getBookingsByIdQuery);
            statement.setString(1, commandRequest.getParameter().get(uidPosition).getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commandResult.getResult().add(
                        new Booking(
                                resultSet.getString("reservationOwner"),
                                resultSet.getString("roomName"), resultSet.getString("beginTime"),
                                resultSet.getString("endTime")
                        )
                );
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
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
