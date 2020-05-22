package pt.isel.ls.handler.room.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.room.RoomHandler;
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
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getRoomsByIdQuery = "SELECT * from rooms WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(getRoomsByIdQuery);
        statement.setString(1, commandRequest.getParametersByName(idArgument).get(0));
        ResultSet resultSet = statement.executeQuery();
        Room roomResult;
        resultSet.next();
        String roomName = resultSet.getString("name");
        String roomLocation = resultSet.getString("location");
        int roomCapacity = resultSet.getInt("capacity");
        String roomDescription = resultSet.getString("description");
        List<Label> labels = getRoomLabels(connection, roomName);
        roomResult = new Room(roomName, roomLocation, roomCapacity, roomDescription);
        roomResult.setLabels(labels);
        return new GetRoomByIdView(roomResult);
    }

    @Override
    public String description() {
        return "Show the detailed information for the room identified by rid";
    }
}
