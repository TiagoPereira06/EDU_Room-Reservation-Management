package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class UserHandler implements CommandHandler {
    public final String idArgument = "{uid}";
    public final String nameParameter = "name";
    public final String emailParameter = "email";


    public boolean checksIfEmailAlreadyExists(String email, Connection connection) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users  "
                + "WHERE email = ?");
        statement.setString(1, email);
        return statement.executeQuery().next();
    }

}
