package pt.isel.ls.handler.room;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRoom extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        CommandResult commandResult = new CommandResult();

        String getRoomsQuery = "SELECT * from rooms";
        PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String roomName = resultSet.getString("name");
            String roomLocation = resultSet.getString("location");
            int roomCapacity = resultSet.getInt("capacity");
            String roomDescription = resultSet.getString("description");
            commandResult.getResult().add(new Room(roomName, roomLocation, roomCapacity, roomDescription));
        }

        return commandResult;
    }


    @Override
    public String description() {
        return "Show all rooms";
    }
}

