package pt.isel.ls.handler.user;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.Queries;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostUser implements CommandHandler {
    private final int emailPosition = 0;
    private final int usernamePosition = 1;

    @Override
    public CommandResult execute(CommandRequest commandRequest) {

        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        String email = "";
        String username = "";
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection();
            String postUserQuery = "INSERT INTO users(email, username) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(postUserQuery);
            email = commandRequest.getParameter().get(emailPosition).getValue().replace('+', ' ');
            username = commandRequest.getParameter().get(usernamePosition).getValue().replace('+', ' ');
            statement.setString(1, email);
            statement.setString(2, username);
            statement.executeQuery();
            /* Verifica se o email já está a ser usado */
            if (Queries.checksIfEmailAlreadyExists(email, connection)) {
                throw new SQLException("Email is already in use");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        commandResult.getResult().add("USER INSERTED: USER INFO -> ".concat(email));
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new user";
    }


}
