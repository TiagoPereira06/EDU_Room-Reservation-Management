package pt.isel.ls.handler.label;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostLabel extends LabelHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        final CommandResult commandResult = new CommandResult();
        final String labelName;
        String getRoomsQuery = "INSERT INTO labels(name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
        labelName = commandRequest.getParametersByName(nameParameter).get(0).getValue().replace('+', ' ');
        statement.setString(1, labelName);
        if (checkIfLabelAlreadyExists(labelName, connection)) {
            throw new SQLException("LABEL ALREADY IN USE !");
        }
        statement.executeUpdate();
        commandResult.getResult().add("LABEL INSERTED: LABEL INFO -> ".concat(labelName));
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
