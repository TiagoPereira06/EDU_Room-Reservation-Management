package pt.isel.ls.handler.room.search;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class SearchRoom implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {
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
            return new SearchRoomView(labelsResult);
        });
    }

    @Override
    public String description() {
        return null;
    }
}
