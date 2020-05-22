package pt.isel.ls.handler.label.post;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostLabel extends LabelHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        final String labelName;
        String getRoomsQuery = "INSERT INTO labels(name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
        try {
            labelName = commandRequest.getParametersByName(nameParameter).get(0);
            statement.setString(1, labelName);
        } catch (IndexOutOfBoundsException exception) {
            throw new SQLException("Missing Arguments");
        }
        if (checkIfLabelAlreadyExists(labelName, connection)) {
            throw new SQLException("LABEL ALREADY IN USE !");
        }
        statement.executeUpdate();
        return new PostLabelView(labelName);
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
