package pt.isel.ls.handler.room.getbyid;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class GetRoomById extends RoomHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws SQLException, ParseException {

        return commandRequest.transactionManager.execute((connection) -> {
            String getRoomsByIdQuery = "select * from rooms as r"
                    + " full join bookings as b"
                    + " on r.name = b.roomname"
                    + " where r.name = ?";
            PreparedStatement statement = connection.prepareStatement(getRoomsByIdQuery);
            statement.setString(1, commandRequest.getParametersByName(idArgument).get(0));
            ResultSet resultSet = statement.executeQuery();
            Room roomResult;
            List<Booking> bookingsResult = new LinkedList<>();
            resultSet.next();
            String roomName = resultSet.getString("name");
            String roomLocation = resultSet.getString("location");
            int roomCapacity = resultSet.getInt("capacity");
            String roomDescription = resultSet.getString("description");
            List<Label> labels = getRoomLabels(connection, roomName);
            roomResult = new Room(roomName, roomLocation, roomCapacity, roomDescription);
            roomResult.setLabels(labels);
            Booking b;
            try {
                b = new Booking(
                        resultSet.getInt("bid"),
                        resultSet.getString("reservationOwner"),
                        resultSet.getString("roomName"),
                        resultSet.getString("beginTime"),
                        resultSet.getString("endTime"));
                bookingsResult.add(b);
            } catch (NullPointerException e) {
                return new Model(roomResult, Collections.emptyList());
            }

            while (resultSet.next()) {
                try {
                    b = new Booking(
                            resultSet.getInt("bid"),
                            resultSet.getString("reservationOwner"),
                            resultSet.getString("roomName"),
                            resultSet.getString("beginTime"),
                            resultSet.getString("endTime"));
                    bookingsResult.add(b);
                } catch (NullPointerException e) {
                    break;
                }
            }

            return new Model(roomResult, bookingsResult);
        });
    }

    @Override
    public String description() {
        return "Show the detailed information for the room identified by rid";
    }
}
