package pt.isel.ls.handler.label.getall;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.label.LabelHandler;
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
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws SQLException {
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
        return new GetLabelView(labelsResult);

    }

    @Override
    public String description() {
        return "Show all labels";
    }
}
