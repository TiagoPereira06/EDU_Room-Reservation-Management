package pt.isel.ls.handler.room.post;

import pt.isel.ls.errors.command.*;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.handler.room.post.getform.PostRoomFormResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static pt.isel.ls.utils.UtilMethods.checkValid;


public class PostRoom extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            final String roomName;
            final int roomCapacity;
            final String roomDescription;
            final String roomLocation;
            final List<String> labelsParameters;
            String postRoomsQuery = "INSERT INTO rooms(name, location, capacity, description) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(postRoomsQuery);
            try {
                roomName = commandRequest.getParameterByName(nameParameter);
                checkValid(roomName, nameParameter);
                statement.setString(1, roomName);

                roomLocation = commandRequest.getParameterByName(locationParameter);
                checkValid(roomLocation, locationParameter);
                statement.setString(2, roomLocation);

                roomCapacity = Integer.parseInt(commandRequest.getParameterByName(capacityParameter));
                checkValid(String.valueOf(roomCapacity), capacityParameter);
                statement.setInt(3, roomCapacity);

                roomDescription = commandRequest.getParameterByName(descriptionParameter);
                checkValid(roomDescription, descriptionParameter);
                statement.setString(4, roomDescription);

                labelsParameters = commandRequest.getParametersByName(labelParameter);
                checkValid(String.valueOf(labelsParameters), labelParameter);

            } catch (NoSuchElementException exception) {
                String tag = exception.getMessage();
                commandRequest.getPostParameters().addErrorMsg(tag, "Missing " + tag);
                throw new MissingArgumentsException(getPostRoomFormResult(commandRequest, connection));
            }


            List<Label> labels = new LinkedList<>();
            for (String labelName : labelsParameters) {
                if (!checkIfLabelAlreadyExists(labelName, connection)) {
                    commandRequest.getPostParameters().addErrorMsg("label", "Label not Found");
                    throw new InvalidArgumentException(getPostRoomFormResult(commandRequest, connection));
                } else {
                    labels.add(new Label(labelName));
                }
            }
            if (checksIfRoomAlreadyExists(roomName, connection)) {
                commandRequest.getPostParameters().addErrorMsg("name", "Duplicate Name Found");
                throw new ConflictArgumentException(getPostRoomFormResult(commandRequest, connection));
            }
            statement.executeUpdate();
            insertLabelsRoom(connection, roomName, labels);
            Room result = new Room(roomName, roomLocation, roomCapacity, roomDescription);
            result.setLabels(labels);
            return new PostRoomResult(result.getName(), "/rooms/" + roomName);
        });
    }

    private PostRoomFormResult getPostRoomFormResult(
            CommandRequest commandRequest, Connection connection) throws InternalDataBaseException {
        return new PostRoomFormResult(
                LabelHandler.getAllLabels(connection),
                commandRequest.getPostParameters()
        );
    }

    @Override
    public String description() {
        return "Creates new room";
    }
}
