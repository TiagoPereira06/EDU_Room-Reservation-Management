package pt.isel.ls.handler.room.getbylabel;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class GetRoomsByLabel extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) -> {
            String getRoomsByLabelQuery = "SELECT r.name,r.location,r.capacity,r.description from rooms as r "
                    + "FULL JOIN roomlabels as rm ON r.name = rm.roomName WHERE rm.label = ?";
            PreparedStatement statement = connection.prepareStatement(getRoomsByLabelQuery);
            final String labelName = commandRequest.getParameterByName(lidArgument);
            statement.setString(1, labelName);
            ResultSet resultSet = statement.executeQuery();
            List<Room> roomResult = new LinkedList<>();
            while (resultSet.next()) {
                String roomName = resultSet.getString("name");
                String roomLocation = resultSet.getString("location");
                int roomCapacity = resultSet.getInt("capacity");
                String roomDescription = resultSet.getString("description");
                Room room = new Room(roomName, roomLocation, roomCapacity, roomDescription);
                //room.setLabels(getRoomLabels(connection,roomName));
                roomResult.add(room);
            }

            return new GetRoomByLabelResult(roomResult, labelName);
        });
    }

    @Override
    public String description() {
        return "Shows a list with all the rooms having label lid";
    }
}
