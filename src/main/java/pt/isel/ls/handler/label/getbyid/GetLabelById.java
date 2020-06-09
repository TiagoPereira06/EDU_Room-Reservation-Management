package pt.isel.ls.handler.label.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.model.Label;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GetLabelById extends LabelHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest) throws Exception {
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
            Room r;
            try {
                r = new Room(resultSet.getString("roomname"));
                roomsResult.add(r);
            } catch (NullPointerException e) {
                return new GetLabelByIdView(labelResult, Collections.emptyList());
            }
            while (resultSet.next()) {
                try {
                    r = new Room(resultSet.getString("roomname"));
                    roomsResult.add(r);
                } catch (NullPointerException e) {
                    break;
                }
            }
            return new GetLabelByIdView(labelResult, roomsResult);
        });
    }

    @Override
    public String description() {
        return "Gets Label Details";
    }
}
