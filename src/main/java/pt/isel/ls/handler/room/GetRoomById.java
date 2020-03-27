package pt.isel.ls.handler.room;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class GetRoomById extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getRoomsByIdQuery = "SELECT * from rooms WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(getRoomsByIdQuery);
            statement.setString(1, commandRequest.getParametersByName(idArgument).get(0).getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roomName = resultSet.getString("name");
                String roomLocation = resultSet.getString("location");
                int roomCapacity = resultSet.getInt("capacity");
                String roomDescription = resultSet.getString("description");
                List<Label> labels = getRoomLabels(connection, roomName);
                Room m = new Room(roomName, roomLocation, roomCapacity, roomDescription);
                m.setLabels(labels);
                commandResult.getResult().add(m);
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
        return "Show the detailed information for the room identified by rid";
    }
}
