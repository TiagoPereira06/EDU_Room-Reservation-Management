package pt.isel.ls.handler.room;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.room.result.GetRoomResult;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GetRoom extends RoomHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getRoomsQuery = "SELECT * from rooms";
        PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> roomResult = new LinkedList<>();
        while (resultSet.next()) {
            String roomName = resultSet.getString("name");
            String roomLocation = resultSet.getString("location");
            int roomCapacity = resultSet.getInt("capacity");
            String roomDescription = resultSet.getString("description");
            roomResult.add(new Room(roomName, roomLocation, roomCapacity, roomDescription).parsePropertiesList());
        }

        return new GetRoomResult(roomResult);
    }

    @Override
    public String description() {
        return "Show all rooms";
    }
}

