package pt.isel.ls.handler.room;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.booking.result.PostBookingResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class PostRoom extends RoomHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        final String roomName;
        final int roomCapacity;
        final String roomDescription;
        final String roomLocation;
        String postRoomsQuery = "INSERT INTO rooms(name, location, capacity, description) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(postRoomsQuery);
        roomName = commandRequest.getParametersByName(nameParameter).get(0);
        statement.setString(1, roomName);
        roomLocation = commandRequest.getParametersByName(locationParameter).get(0);
        statement.setString(2, roomLocation);
        roomCapacity = Integer.parseInt(commandRequest.getParametersByName(capacityParameter).get(0));
        statement.setInt(3, roomCapacity);
        roomDescription = commandRequest.getParametersByName(descriptionParameter).get(0);
        statement.setString(4, roomDescription);

        List<String> labelsParameters = commandRequest.getParametersByName(labelParameter);
        List<Label> labels = new LinkedList<>();
        for (String labelName : labelsParameters) {
            if (!checkIfLabelAlreadyExists(labelName, connection)) {
                throw new SQLException("LABEL NOT VALID!");
            } else {
                labels.add(new Label(labelName));
            }
        }
        statement.executeUpdate();
        insertLabelsRoom(connection, roomName, labels);
        List<List<String>> roomResult = new LinkedList<>();
        roomResult.add(Collections.singletonList(roomName));
        return new PostBookingResult(roomResult);
    }

    @Override
    public String description() {
        return "Creates new room";
    }
}
