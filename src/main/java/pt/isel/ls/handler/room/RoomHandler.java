package pt.isel.ls.handler.room;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.model.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class RoomHandler extends LabelHandler implements CommandHandler {
    final String idArgument = "{rid}";
    final String lidArgument = "{lid}";
    final String nameParameter = "name";
    final String locationParameter = "location";
    final String capacityParameter = "capacity";
    final String descriptionParameter = "description";
    final String labelParameter = "label";


    public List<Label> getRoomLabels(Connection connection, String roomName) throws SQLException {
        List<Label> labels = new LinkedList<>();
        String getLabels = "SELECT label FROM roomlabels WHERE roomname = ?";
        PreparedStatement statement = connection.prepareStatement(getLabels);
        statement.setString(1, roomName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            labels.add(new Label(resultSet.getString("label")));
        }
        return labels;
    }

    public boolean checksIfRoomAlreadyExists(String roomName, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM rooms  "
                + "WHERE name = ?");
        statement.setString(1, roomName);
        return statement.executeQuery().next();

    }

    public void insertLabelsRoom(Connection connection, String roomName, List<Label> labels) throws SQLException {
        String insertLabelQuery = "INSERT INTO roomlabels(roomName, label) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(insertLabelQuery);
        for (Label l : labels) {
            statement.setString(1, roomName);
            statement.setString(2, l.getName().replace('+', ' '));
            statement.executeUpdate();
        }
    }

}

