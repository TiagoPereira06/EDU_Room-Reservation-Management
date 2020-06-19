package pt.isel.ls.handler.label.getall;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.model.Label;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class GetLabel extends LabelHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
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
            return new GetLabelResult(labelsResult);
        });
    }

    @Override
    public String description() {
        return "Show all labels";
    }
}
