package pt.isel.ls.handler.room;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.room.result.GetCompleteRoomResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class GetRoomById extends RoomHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getRoomsByIdQuery = "SELECT * from rooms WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(getRoomsByIdQuery);
        statement.setString(1, commandRequest.getParametersByName(idArgument).get(0).getValue());
        ResultSet resultSet = statement.executeQuery();
        Room room = null;
        List<List<String>> roomResult = new LinkedList<>();
        while (resultSet.next()) {
            String roomName = resultSet.getString("name");
            String roomLocation = resultSet.getString("location");
            int roomCapacity = resultSet.getInt("capacity");
            String roomDescription = resultSet.getString("description");
            List<Label> labels = getRoomLabels(connection, roomName);
            room = new Room(roomName, roomLocation, roomCapacity, roomDescription);
            room.setLabels(labels);
            roomResult.add(room.parsePropertiesList());
        }
        return new GetCompleteRoomResult(roomResult, room != null ? room.getLabels().size() : 0);
    }

    @Override
    public String description() {
        return "Show the detailed information for the room identified by rid";
    }
}
