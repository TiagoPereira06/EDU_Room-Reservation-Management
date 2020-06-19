package pt.isel.ls.handler.user;

import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class UserHandler implements CommandHandler {
    public final String idArgument = "{uid}";
    public final String nameParameter = "name";
    public final String emailParameter = "email";

    public static List<User> getAllUsers(Connection connection) throws InternalDataBaseException {
        try {
            String getUsersQuery = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(getUsersQuery);
            ResultSet resultSet = statement.executeQuery();
            List<User> userResult = new LinkedList<>();
            while (resultSet.next()) {
                userResult.add(new User(resultSet.getString("email"), resultSet.getString("username")));
            }
            return userResult;
        } catch (SQLException e) {
            throw new InternalDataBaseException();
        }
    }

    public boolean checksIfEmailAlreadyExists(String email, Connection connection) throws InternalDataBaseException {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users  "
                    + "WHERE email = ?");
            statement.setString(1, email);
            return statement.executeQuery().next();
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }
}
