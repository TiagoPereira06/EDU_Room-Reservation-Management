package pt.isel.ls.handler.user;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.user.result.GetUserResult;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class GetUserById extends UserHandler {

    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getUsersByIdQuery = "SELECT * "
                + "FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
        statement.setString(1, commandRequest.getParametersByName(idArgument).get(0).getValue());
        ResultSet resultSet = statement.executeQuery();
        List<List<String>> userResult = new LinkedList<>();
        while (resultSet.next()) {
            userResult.add(
                    new User(
                            resultSet.getString("email"),
                            resultSet.getString("username")
                    ).parsePropertiesList()
            );
        }
        return new GetUserResult(userResult);
    }

    @Override
    public String description() {
        return "Show users with id";
    }


}
