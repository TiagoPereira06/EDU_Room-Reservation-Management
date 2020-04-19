package pt.isel.ls.handler.user;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.user.result.PostUserResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class PostUser extends UserHandler {

    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws SQLException {
        String email;
        String postUserQuery = "INSERT INTO users(email, username) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(postUserQuery);
        email = commandRequest.getParametersByName(emailParameter).get(0).getValue().replace('+', ' ');
        String username = commandRequest.getParametersByName(nameParameter).get(0).getValue().replace('+', ' ');
        statement.setString(1, email);
        statement.setString(2, username);
        /* Verifica se o email já está a ser usado */
        if (checksIfEmailAlreadyExists(email, connection)) {
            throw new SQLException("EMAIL ALREADY IN USE");
        }
        statement.executeUpdate();
        List<List<String>> userResult = new LinkedList<>();
        userResult.add(Collections.singletonList(email));

        return new PostUserResult(userResult);
    }

    @Override
    public String description() {
        return "Creates new user";
    }


}
