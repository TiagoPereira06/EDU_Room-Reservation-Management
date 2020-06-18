package pt.isel.ls.handler.label.getbyid;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GetLabelById extends LabelHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {
            String getUsersByIdQuery = "select * from labels"
                    + " as l FULL join roomlabels as rm"
                    + " on l.name = rm.label"
                    + " where l.name = ?";
            PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
            final String s = commandRequest.getParametersByName(idArgument).get(0);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            Label labelResult;
            List<Room> roomsResult = new LinkedList<>();
            resultSet.next();
            labelResult = new Label(
                    resultSet.getString("name"));
            try {
                getRoom(resultSet, roomsResult);
            } catch (NullPointerException e) {
                return new GetLabelByIdResult(labelResult, Collections.emptyList());
            }
            while (resultSet.next()) {
                try {
                    getRoom(resultSet, roomsResult);
                } catch (NullPointerException e) {
                    break;
                }
            }
            return new GetLabelByIdResult(labelResult, roomsResult);
        });
    }

    private void getRoom(ResultSet resultSet, List<Room> roomsResult) throws SQLException {
        Room r = new Room(resultSet.getString("roomname"));
        if (r.getName() == null) {
            throw new NullPointerException();
        }
        roomsResult.add(r);
    }

    @Override
    public String description() {
        return "Gets Label Details";
    }
}
