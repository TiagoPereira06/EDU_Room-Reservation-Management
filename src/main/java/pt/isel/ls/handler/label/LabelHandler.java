package pt.isel.ls.handler.label;

import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.model.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class LabelHandler implements CommandHandler {
    public final String idArgument = "{lid}";
    public final String nameParameter = "name";

    public static List<Label> getAllLabels(Connection connection) throws InternalDataBaseException {
        try {
            String getLabelsQuery = "SELECT * FROM labels";
            PreparedStatement statement = connection.prepareStatement(getLabelsQuery);
            ResultSet resultSet = statement.executeQuery();
            List<Label> labelsResult = new LinkedList<>();
            while (resultSet.next()) {
                labelsResult.add(
                        new Label(
                                resultSet.getString("name"))
                );
            }
            return labelsResult;
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }

    public boolean checkIfLabelAlreadyExists(String label, Connection connection) throws InternalDataBaseException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM labels WHERE name = ?");
            statement.setString(1, label.replace('+', ' '));
            return statement.executeQuery().next();
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }

}
