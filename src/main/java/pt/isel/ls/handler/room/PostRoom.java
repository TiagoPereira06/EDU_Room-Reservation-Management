package pt.isel.ls.handler.room;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PostRoom implements CommandHandler {
    private final int roomNamePosition = 0;
    private final int roomLocationPosition = 1;
    private final int roomCapacityPosition = 2;
    private final int roomDescriptionPosition = 3;

    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        CommandResult commandResult = new CommandResult();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        Connection connection = null;
        String roomName = "";
        String roomLocation = "";
        int roomCapacity = 0;
        String roomDescription = "";
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection();
            String postRoomsQuery = "INSERT INTO rooms(name, location, capacity, description) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(postRoomsQuery);
            roomName = commandRequest.getParameter().get(roomNamePosition).getValue()
                    .replace('+', ' ');
            statement.setString(1, roomName);
            roomLocation = commandRequest.getParameter().get(roomLocationPosition).getValue()
                    .replace('+', ' ');
            statement.setString(2, roomLocation);
            //VOLTAR A VER ROOMCAPACITY
            roomCapacity = commandRequest.getParameter().get(roomCapacityPosition).getValue()
                    .indexOf(roomCapacityPosition);
            statement.setInt(3, roomCapacity);
            roomDescription = commandRequest.getParameter().get(roomDescriptionPosition).getValue()
                    .replace('+', ' ');
            statement.setString(4, roomDescription);
            statement.executeQuery();

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
        commandResult.getResult().add("ROOM INSERTED: ROOM INFO -> ".concat(roomName));
        return commandResult;
    }

    @Override
    public String description() {
        return "Creates new room";
    }
}
