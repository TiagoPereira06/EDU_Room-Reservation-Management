package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;


public abstract class BookingHandler implements CommandHandler {
    final String idArgument = "{bid}";
    final String ownerIdArgument = "{uid}";
    final String ownerIdParameter = "uid";
    final String beginParameter = "begin";
    final String durationParameter = "duration";
    final String roomIdParameter = "room";

    boolean checkIfRoomIsAvailable(Connection connection, String name,
                                   Date beginDateUser, Date endDateUser)
            throws SQLException, ParseException {
        String getBookingsByIdQuery = "SELECT begintime, endtime FROM bookings"
                + " WHERE roomname = ?"
                + "ORDER BY begintime DESC";
        PreparedStatement statement = connection.prepareStatement(getBookingsByIdQuery);
        statement.setString(1, name);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            //Transformar resultados da BD em Dates e comparar com os param
            Date beginDateDb = Booking.dateFormat.parse(resultSet.getString("begintime"));
            Date endDateDb = Booking.dateFormat.parse(resultSet.getString("endtime"));
            if (beginDateUser.compareTo(endDateDb) <= 0 && endDateUser.compareTo(beginDateDb) >= 0) {
                return false;
            }
        }
        return true;
    }
}


