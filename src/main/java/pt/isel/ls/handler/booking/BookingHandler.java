package pt.isel.ls.handler.booking;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static pt.isel.ls.utils.UtilMethods.formatDateToString;
import static pt.isel.ls.utils.UtilMethods.formatStringToDate;


public abstract class BookingHandler implements CommandHandler {
    final String idArgument = "{bid}";
    final String roomIdArgument = "{rid}";
    final String ownerIdArgument = "{uid}";
    final String ownerIdParameter = "uid";
    final String beginParameter = "begin";
    final String durationParameter = "duration";
    final String roomIdParameter = "room";

    public static Date parseDuration(String duration, Date beginTime) {
        return new Date(beginTime.getTime() + 60000 * Integer.parseInt(duration));
    }

    protected boolean checkIfRoomIsAvailable(Connection connection, String roomName,
                                             Date beginDate, Date endDate)
            throws SQLException, ParseException {
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
    }

    protected int getNextBookingId(Connection connection) throws SQLException {
        String getBookingId = "SELECT bid FROM bookings ORDER BY bid DESC";
        PreparedStatement statement = connection.prepareStatement(getBookingId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getInt("bid");
    }

    protected String getBeginTime(CommandRequest commandRequest) {
        return commandRequest.getParametersByName(beginParameter).get(0);
    }

    protected String getDuration(CommandRequest commandRequest) {
        return commandRequest.getParametersByName(durationParameter).get(0);
    }

    protected void checkDuration(String duration) throws SQLException {
        int durationTime = Integer.parseInt(duration);
        if (durationTime < 10 || durationTime % 10 != 0) {
            throw new SQLException("INVALID DURATION");
        }
    }


}




