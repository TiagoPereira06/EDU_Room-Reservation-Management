package pt.isel.ls.handler.booking;

import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.errors.command.InvalidArgumentException;
import pt.isel.ls.handler.CommandHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static pt.isel.ls.utils.UtilMethods.formatDateToString;
import static pt.isel.ls.utils.UtilMethods.formatStringToDate;


public abstract class BookingHandler implements CommandHandler {
    public static final String roomIdArgument = "{rid}";
    public final String idArgument = "{bid}";
    public final String ownerIdArgument = "{uid}";
    public final String ownerIdParameter = "uid";
    public final String beginParameter = "begin";
    public final String durationParameter = "duration";
    public final String roomIdParameter = "room";

    public static Date parseDuration(String duration, Date beginTime) {
        return new Date(beginTime.getTime() + 60000 * Integer.parseInt(duration));
    }

    public boolean checkIfRoomIsAvailable(Connection connection, String roomName,
                                          Date beginDate, Date endDate)
            throws InternalDataBaseException, InvalidArgumentException {
        try {

            String getBookingsByIdQuery = "SELECT begintime, endtime FROM bookings "
                    + "WHERE roomname = ? AND (endtime::DATE) >= ?::DATE";

            PreparedStatement statement = connection.prepareStatement(getBookingsByIdQuery);
            statement.setString(1, roomName);
            //noinspection JpaQueryApiInspection
            statement.setString(2, formatDateToString(beginDate));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Transformar resultados da BD em Dates e comparar com os param
                Date beginDateDb = formatStringToDate(resultSet.getString("begintime"));
                Date endDateDb = formatStringToDate(resultSet.getString("endtime"));
                if (beginDate.compareTo(endDateDb) <= 0 && endDate.compareTo(beginDateDb) >= 0) {
                    return false;
                }
            }
            return true;
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        } catch (ParseException e) {
            throw new InvalidArgumentException(e.getMessage());
        }
    }

    public int getNextBookingId(Connection connection) throws InternalDataBaseException {
        try {

            String getBookingId = "SELECT bid FROM bookings ORDER BY bid DESC";
            PreparedStatement statement = connection.prepareStatement(getBookingId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("bid");
        } catch (SQLException throwables) {
            throw new InternalDataBaseException();
        }
    }


    public void checkIfDurationIsValid(String duration) throws InvalidArgumentException {
        int durationTime = Integer.parseInt(duration);
        if (durationTime < 10) {
            throw new InvalidArgumentException("Must be higher than 10");
        } else if (durationTime % 10 != 0) {
            throw new InvalidArgumentException("Must be a multiple of 10");
        }
    }
}




