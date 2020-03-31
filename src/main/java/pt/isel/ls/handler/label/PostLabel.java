package pt.isel.ls.handler.label;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostLabel extends LabelHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        String labelName = "";
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String getRoomsQuery = "INSERT INTO labels(name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
            labelName = commandRequest.getParametersByName(nameParameter).get(0).getValue().replace('+', ' ');
            statement.setString(1, labelName);
            if (checkIfLabelAlreadyExists(labelName, connection)) {
                throw new SQLException("LABEL ALREADY IN USE !");
            }
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
                commandResult.getResult().add(e.getMessage());
            } catch (SQLException ex) {
                commandResult.getResult().add(e.getMessage());
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                commandResult.getResult().add(e.getMessage());
            }
        }
        if (commandResult.getResult().isEmpty()) {
            commandResult.getResult().add("LABEL INSERTED: LABEL INFO -> ".concat(labelName));
        }

        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
