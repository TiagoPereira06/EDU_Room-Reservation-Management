package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserHandler implements CommandHandler {
    final String idArgument = "{uid}";
    final String nameParameter = "name";
    final String emailParameter = "email";

    public boolean checksIfEmailAlreadyExists(String email, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users  "
                + "WHERE email = ?");
        statement.setString(1, email);
        return statement.executeQuery().next();
    }

}
