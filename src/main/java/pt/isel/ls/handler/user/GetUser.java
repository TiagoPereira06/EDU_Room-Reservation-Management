package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GetUser extends UserHandler {


    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        Connection connection = null;
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String getUsersQuery = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(getUsersQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commandResult.getResult().add(new User(resultSet.getString("username"), resultSet.getString("email")));
            }
        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return commandResult;

    }

    @Override
    public String description() {
        return "Show all users";
    }


}
