package pt.isel.ls.handler.room;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.model.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class RoomHandler implements CommandHandler {
    public List<Label> getRoomLabels(String roomName) {
        List<Label> labels = new LinkedList<>();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getLabels = "SELECT label FROM roomlabels WHERE roomname = ?";
            PreparedStatement statement = connection.prepareStatement(getLabels);
            statement.setString(1, roomName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                labels.add(new Label(resultSet.getString("label")));
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return labels;
    }
}

