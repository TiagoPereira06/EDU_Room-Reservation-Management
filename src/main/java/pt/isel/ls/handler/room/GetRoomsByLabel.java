package pt.isel.ls.handler.room;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetRoomsByLabel extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getRoomsByLabelQuery = "SELECT r.name,r.location,r.capacity,r.description from rooms as r "
                    + "INNER JOIN roomlabels as rm ON r.name = rm.roomName WHERE rm.label = ?";
            PreparedStatement statement = connection.prepareStatement(getRoomsByLabelQuery);
            statement.setString(1, commandRequest.getParametersByName(lidArgument).get(0).getValue());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String roomName = resultSet.getString("name");
                String roomLocation = resultSet.getString("location");
                int roomCapacity = resultSet.getInt("capacity");
                String roomDescription = resultSet.getString("description");
                commandResult.getResult().add(new Room(roomName, roomLocation, roomCapacity, roomDescription));
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
        return "Shows a list with all the rooms having label lid";
    }
}
