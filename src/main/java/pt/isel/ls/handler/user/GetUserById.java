package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserById extends UserHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        CommandResult commandResult = new CommandResult();

        String getUsersByIdQuery = "SELECT * "
                + "FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
        statement.setString(1, commandRequest.getParametersByName(idArgument).get(0).getValue());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            commandResult.getResult().add(
                    new User(
                            resultSet.getString("email"),
                            resultSet.getString("username")
                    )
            );
        }
        return commandResult;
    }

    @Override
    public String description() {
        return "Show users with id";
    }


}
