package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PostUser extends UserHandler {

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        dataSource.setUrl(url);
        String email = "";
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String postUserQuery = "INSERT INTO users(email, username) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(postUserQuery);
            email = commandRequest.getParametersByName(emailParameter).get(0).getValue().replace('+', ' ');
            String username = commandRequest.getParametersByName(nameParameter).get(0).getValue().replace('+', ' ');
            statement.setString(1, email);
            statement.setString(2, username);
            /* Verifica se o email já está a ser usado */
            if (checksIfEmailAlreadyExists(email, connection)) {
                throw new SQLException("EMAIL ALREADY IN USE !");
            }
            statement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
                commandResult.getResult().add(e.getMessage());
            } catch (SQLException ex) {
                commandResult.getResult().add(ex.getMessage());
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                commandResult.getResult().add(e.getMessage());
            }
        }
        if (commandResult.getResult().isEmpty()) {
            commandResult.getResult().add("USER INSERTED: USER INFO -> ".concat(email));
        }
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new user";
    }


}
