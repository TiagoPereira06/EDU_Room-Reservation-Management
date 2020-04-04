package pt.isel.ls.handler.room;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Parameter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class PostRoom extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        final CommandResult commandResult = new CommandResult();
        final String roomName;
        final int roomCapacity;
        final String roomDescription;
        final String roomLocation;
        String postRoomsQuery = "INSERT INTO rooms(name, location, capacity, description) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(postRoomsQuery);
        roomName = commandRequest.getParametersByName(nameParameter).get(0).getValue()
                .replace('+', ' ');
        statement.setString(1, roomName);
        roomLocation = commandRequest.getParametersByName(locationParameter).get(0).getValue()
                .replace('+', ' ');
        statement.setString(2, roomLocation);
        roomCapacity = Integer.parseInt(commandRequest.getParametersByName(capacityParameter).get(0).getValue());
        statement.setInt(3, roomCapacity);
        roomDescription = commandRequest.getParametersByName(descriptionParameter).get(0).getValue()
                .replace('+', ' ');
        statement.setString(4, roomDescription);

        List<Parameter> labelsParameters = commandRequest.getParametersByName(labelParameter);
        List<Label> labels = new LinkedList<>();
        for (Parameter p : labelsParameters) {
            if (!checkIfLabelAlreadyExists(p.getValue(), connection)) {
                throw new SQLException("LABEL NOT VALID!");
            } else {
                labels.add(new Label(p.getValue()));
            }
        }
        statement.executeUpdate();
        insertLabelsRoom(connection, roomName, labels);
        commandResult.getResult().add("ROOM INSERTED: ROOM INFO -> ".concat(roomName));
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new room";
    }
}
