package pt.isel.ls.handler.label.post;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostLabel extends LabelHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {

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
            return new Model(labelName);
        });
    }

    @Override
    public String description() {
        return "Creates new label";
    }
}
