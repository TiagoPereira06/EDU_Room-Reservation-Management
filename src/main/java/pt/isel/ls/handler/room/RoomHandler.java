package pt.isel.ls.handler.room;

import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.errors.command.InvalidArgumentException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static pt.isel.ls.utils.UtilMethods.formatDateToString;
import static pt.isel.ls.utils.UtilMethods.formatStringToDate;

public abstract class RoomHandler extends LabelHandler implements CommandHandler {
    public final String idArgument = "{rid}";
    public final String lidArgument = "{lid}";
    public final String nameParameter = "name";
    public final String locationParameter = "location";
    public final String capacityParameter = "capacity";
    public final String beginParameter = "begin";
    public final String durationParameter = "duration";
    public final String descriptionParameter = "description";
    public final String labelParameter = "label";


    public List<Label> getRoomLabels(Connection connection, String roomName) throws InternalDataBaseException {
        try {

            List<Label> labels = new LinkedList<>();
            String getLabels = "SELECT label FROM roomlabels WHERE roomname = ?";
            PreparedStatement statement = connection.prepareStatement(getLabels);
            statement.setString(1, roomName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                labels.add(new Label(resultSet.getString("label")));
            }
            return labels;
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }

    public boolean checksIfRoomAlreadyExists(String roomName, Connection connection) throws InternalDataBaseException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms  "
                    + "WHERE name = ?");
            statement.setString(1, roomName);
            return statement.executeQuery().next();
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }

    }

    public void insertLabelsRoom(Connection connection, String roomName, List<Label> labels)
            throws InternalDataBaseException {
        try {
            String insertLabelQuery = "INSERT INTO roomlabels(roomName, label) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(insertLabelQuery);
            for (Label l : labels) {
                statement.setString(1, roomName);
                statement.setString(2, l.getName().replace('+', ' '));
                statement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }

    public List<Room> getAvailableRooms(Connection connection,
                                        Date beginDate, Date endDate, List<Room> allRooms)
            throws InternalDataBaseException, InvalidArgumentException {
        try {
            String getAvailableRooms = "SELECT b.begintime, b.endtime, r.name, r.location, r.capacity, r.description "
                    + "FROM bookings as b"
                    + " FULL JOIN rooms as r"
                    + " ON r.name = b.roomname"
                    + " WHERE (endtime::DATE) >= ?::DATE";
            PreparedStatement statement = connection.prepareStatement(getAvailableRooms);
            //noinspection JpaQueryApiInspection
            statement.setString(1, formatDateToString(endDate));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Date beginDateDb = formatStringToDate(resultSet.getString("begintime"));
                Date endDateDb = formatStringToDate(resultSet.getString("endtime"));
                if ((beginDate.compareTo(endDateDb) <= 0 && endDate.compareTo(beginDateDb) >= 0)) {
                    String name = resultSet.getString("roomname");
                    allRooms.removeIf(room -> room.getName().equals(name));
                }
            }
            return allRooms;
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        } catch (ParseException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    public Room getRoom(Connection connection, ResultSet resultSet) throws InternalDataBaseException {
        try {
            String roomName = resultSet.getString("name");
            String roomLocation = resultSet.getString("location");
            int roomCapacity = resultSet.getInt("capacity");
            String roomDescription = resultSet.getString("description");
            Room room = new Room(roomName, roomLocation, roomCapacity, roomDescription);
            room.setLabels(getRoomLabels(connection, roomName));
            return room;
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }

    public List<Room> getAllRoomsWithLabels(Connection connection) throws InternalDataBaseException {
        try {
            List<Room> allRooms = new LinkedList<>();
            String getRoomNamesQuery = "SELECT * from rooms";
            PreparedStatement statement = connection.prepareStatement(getRoomNamesQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allRooms.add(getRoom(connection, resultSet));
            }
            return allRooms;
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }

    }
}


