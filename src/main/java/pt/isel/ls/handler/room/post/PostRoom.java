package pt.isel.ls.handler.room.post;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class PostRoom extends RoomHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {

            final String roomName;
            final int roomCapacity;
            final String roomDescription;
            final String roomLocation;
            String postRoomsQuery = "INSERT INTO rooms(name, location, capacity, description) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(postRoomsQuery);
            try {
                roomName = commandRequest.getParametersByName(nameParameter).get(0);
                statement.setString(1, roomName);
                roomLocation = commandRequest.getParametersByName(locationParameter).get(0);
                statement.setString(2, roomLocation);
                roomCapacity = Integer.parseInt(commandRequest.getParametersByName(capacityParameter).get(0));
                statement.setInt(3, roomCapacity);
                roomDescription = commandRequest.getParametersByName(descriptionParameter).get(0);
                statement.setString(4, roomDescription);
            } catch (IndexOutOfBoundsException exception) {
                throw new SQLException("Missing Arguments");
            }

            List<String> labelsParameters = commandRequest.getParametersByName(labelParameter);
            List<Label> labels = new LinkedList<>();
            for (String labelName : labelsParameters) {
                if (!checkIfLabelAlreadyExists(labelName, connection)) {
                    throw new SQLException("LABEL NOT VALID");
                } else {
                    labels.add(new Label(labelName));
                }
            }
            if (checksIfRoomAlreadyExists(roomName, connection)) {
                throw new SQLException("ROOM ALREADY EXISTS");
            }
            statement.executeUpdate();
            insertLabelsRoom(connection, roomName, labels);
            return new Model(roomName);
        });
    }

    @Override
    public String description() {
        return "Creates new room";
    }
}
