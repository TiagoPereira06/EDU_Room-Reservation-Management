package pt.isel.ls.handler.booking;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.Queries;

import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;


public class PostBooking implements CommandHandler {
    private final int bidPosition = 0;
    private final int reservationOwnerPosition = 1;
    private final int roomNamePosition = 2;
    private final int beginTimePosition = 3;
    private final int endTimePosition = 4;

    @Override
    public CommandResult execute(CommandRequest commandRequest) {

        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        String bid = " ";
        String reservationOwner = "";
        String roomName = "";
        String beginTime = "";
        String endTime = "";
        dataSource.setUrl(url);

        try {
            connection = dataSource.getConnection();
            String postBookingsQuery = "INSERT INTO bookings(bid, reservationOwner, roomName, beginTime, endTime )"
                    + "VALUES (?,?,?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(postBookingsQuery);
            bid = commandRequest.getParameter().get(bidPosition).getValue()
                    .replace('+', ' ');
            statement.setString(1, bid);
            reservationOwner = commandRequest.getParameter().get(reservationOwnerPosition).getValue()
                    .replace('+', ' ');
            statement.setString(2, reservationOwner);
            roomName = commandRequest.getParameter().get(roomNamePosition).getValue()
                    .replace('+', ' ');
            statement.setString(3, roomName);
            beginTime = commandRequest.getParameter().get(beginTimePosition).getValue()
                    .replace('+', ' ');
            statement.setString(4, beginTime);
            endTime = commandRequest.getParameter().get(endTimePosition).getValue()
                    .replace('+', ' ');
            statement.setString(5, endTime);
            statement.executeQuery();

            /* Verifica se já existe room*/
            if (Queries.checksIfRoomAlreadyExists(roomName, connection)) {
                throw new Exception("Room already in usage");
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        commandResult.getResult().add("BOOKING INSERTED: BOOKING INFO -> ".concat(bid));
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new booking";
    }
}
