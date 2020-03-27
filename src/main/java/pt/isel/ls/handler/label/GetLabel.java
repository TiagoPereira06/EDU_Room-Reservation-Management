package pt.isel.ls.handler.label;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetLabel extends LabelHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getLabelsQuery = "SELECT * FROM labels";
            PreparedStatement statement = connection.prepareStatement(getLabelsQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commandResult.getResult().add(new Label(resultSet.getString("name")));
            }
        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return commandResult;
    }

    @Override
    public String description() {
        return "Show all labels";
    }
}
