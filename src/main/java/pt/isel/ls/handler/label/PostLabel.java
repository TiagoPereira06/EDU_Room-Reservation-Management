package pt.isel.ls.handler.label;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.label.result.LabelResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PostLabel extends LabelHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        final String labelName;
        String getRoomsQuery = "INSERT INTO labels(name) VALUES (?)";
        PreparedStatement statement = connection.prepareStatement(getRoomsQuery);
        labelName = commandRequest.getParametersByName(nameParameter).get(0).getValue().replace('+', ' ');
        statement.setString(1, labelName);
        if (checkIfLabelAlreadyExists(labelName, connection)) {
            throw new SQLException("LABEL ALREADY IN USE !");
        }
        statement.executeUpdate();
        List<List<String>> labelsResult = new LinkedList<>();
        labelsResult.add(Collections.singletonList(labelName));
        return new LabelResult(labelsResult, "POST");
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
