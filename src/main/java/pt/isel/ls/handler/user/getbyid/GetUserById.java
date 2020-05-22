package pt.isel.ls.handler.user.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetUserById extends UserHandler {

    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String getUsersByIdQuery = "SELECT * "
                + "FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
        statement.setString(1, commandRequest.getParametersByName(idArgument).get(0));
        ResultSet resultSet = statement.executeQuery();
        User userResult;
        resultSet.next();
        userResult = new User(
                resultSet.getString("email"),
                resultSet.getString("username")
        );

        return new GetUserByIdView(userResult);
    }

    @Override
    public String description() {
        return "Show users with id";
    }


}
