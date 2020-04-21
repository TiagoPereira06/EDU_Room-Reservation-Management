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


public class GetRoomsByLabel extends RoomHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getRoomsByLabelQuery = "SELECT r.name,r.location,r.capacity,r.description from rooms as r "
                + "INNER JOIN roomlabels as rm ON r.name = rm.roomName WHERE rm.label = ?";
        PreparedStatement statement = connection.prepareStatement(getRoomsByLabelQuery);
        statement.setString(1, commandRequest.getParametersByName(lidArgument).get(0));
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> roomResult = new LinkedList<>();
        while (resultSet.next()) {
            String roomName = resultSet.getString("name");
            String roomLocation = resultSet.getString("location");
            int roomCapacity = resultSet.getInt("capacity");
            String roomDescription = resultSet.getString("description");
            roomResult.add(new Room(roomName, roomLocation, roomCapacity, roomDescription).parsePropertiesList(false));
        }

        return new GetRoomResult(roomResult);
    }

    @Override
    public String description() {
        return "Shows a list with all the rooms having label lid";
    }
}
