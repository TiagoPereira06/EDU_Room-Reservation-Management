package pt.isel.ls.handler.label.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetLabelById extends LabelHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws Exception {
        String getUsersByIdQuery = "SELECT * "
                + "FROM labels WHERE name = ?";
        PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
        final String s = commandRequest.getParametersByName(idArgument).get(0);
        statement.setString(1, s);
        ResultSet resultSet = statement.executeQuery();
        Label labelResult;
        resultSet.next();
        labelResult = new Label(
                resultSet.getString("name"));

        return new GetLabelByIdView(labelResult);
    }

    @Override
    public String description() {
        return "Gets Label Details";
    }
}
