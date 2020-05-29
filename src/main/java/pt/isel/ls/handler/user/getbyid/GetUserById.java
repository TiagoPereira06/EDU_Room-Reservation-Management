package pt.isel.ls.handler.user.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.model.User;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GetUserById extends UserHandler {

    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection)
            throws SQLException, ParseException {
        String getUsersByIdQuery = "select * from users"
                + " as u FULL join bookings as b"
                + " on b.reservationowner = u.email "
                + "where u.email = ?";
        PreparedStatement statement = connection.prepareStatement(getUsersByIdQuery);
        statement.setString(1, commandRequest.getParametersByName(idArgument).get(0));
        ResultSet resultSet = statement.executeQuery();
        User userResult;
        List<Booking> bookingResult = new LinkedList<>();
        resultSet.next();
        userResult = new User(
                resultSet.getString("email"),
                resultSet.getString("username")
        );
        Booking b;
        try {
            b = new Booking(resultSet.getInt("bid"), resultSet.getString("roomname"),
                    resultSet.getString("beginTime"), resultSet.getString("endTime"));
            bookingResult.add(b);
        } catch (NullPointerException e) {
            return new GetUserByIdView(userResult, Collections.emptyList());
        }

        while (resultSet.next()) {
            try {
                b = new Booking(resultSet.getInt("bid"), resultSet.getString("roomname"),
                        resultSet.getString("beginTime"), resultSet.getString("endTime"));
                bookingResult.add(b);
            } catch (NullPointerException e) {
                break;
            }

        }
        return new GetUserByIdView(userResult, bookingResult);

    }

    @Override
    public String description() {
        return "Show users with id";
    }
}
