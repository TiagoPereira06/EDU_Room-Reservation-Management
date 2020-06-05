package pt.isel.ls.handler.user.getall;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class GetUser extends UserHandler {

    @Override
    public Model execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {
            String getUsersQuery = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(getUsersQuery);
            ResultSet resultSet = statement.executeQuery();
            List<User> userResult = new LinkedList<>();
            while (resultSet.next()) {
                userResult.add(new User(resultSet.getString("email"), resultSet.getString("username")));
            }
            return new Model(userResult);
        });
    }

    @Override
    public String description() {
        return "Show all users";
    }


}
