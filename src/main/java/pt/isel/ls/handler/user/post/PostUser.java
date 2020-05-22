package pt.isel.ls.handler.user.post;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PostUser extends UserHandler {

    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String email;
        String username;
        String postUserQuery = "INSERT INTO users(email, username) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(postUserQuery);
        try {
            email = commandRequest.getParametersByName(emailParameter).get(0);
            username = commandRequest.getParametersByName(nameParameter).get(0);
        } catch (IndexOutOfBoundsException exception) {
            throw new SQLException("Missing Arguments");
        }
        statement.setString(1, email);
        statement.setString(2, username);
        if (checksIfEmailAlreadyExists(email, connection)) {
            throw new SQLException("EMAIL ALREADY IN USE");
        }
        statement.executeUpdate();
        return new PostUserView(email);
    }

    @Override
    public String description() {
        return "Creates new user";
    }


}
