package pt.isel.ls.handler.label;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.label.result.LabelResult;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class GetLabel extends LabelHandler {
    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getLabelsQuery = "SELECT * FROM labels";
        PreparedStatement statement = connection.prepareStatement(getLabelsQuery);
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> labelsResult = new LinkedList<>();

        while (resultSet.next()) {
            labelsResult.add(new Label(resultSet.getString("name")).parsePropertiesList());
        }
        return new LabelResult(labelsResult, "GET");
    }

    @Override
    public String description() {
        return "Show all labels";
    }
}
