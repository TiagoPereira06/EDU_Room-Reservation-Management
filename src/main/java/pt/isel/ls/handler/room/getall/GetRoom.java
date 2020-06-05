package pt.isel.ls.handler.room.getall;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static pt.isel.ls.utils.UtilMethods.formatStringToDate;

public class GetRoom extends RoomHandler {
    @Override
    public Model execute(CommandRequest commandRequest) throws SQLException, ParseException {
        return commandRequest.transactionManager.execute((connection) ->
                new Model(executeGetCommand(commandRequest, connection)));
    }

    @Override
    public String description() {
        return "Show all rooms";
    }

    private List<Room> executeGetCommand(CommandRequest request, Connection connection)
            throws ParseException, SQLException {
        List<Room> result = getAllRoomsWithLabels(connection);

        if (!request.getParametersByName(beginParameter).isEmpty()
                && !request.getParametersByName(durationParameter).isEmpty()) {
            final String begin = request.getParametersByName(beginParameter).get(0);
            final String duration = request.getParametersByName(durationParameter).get(0);
            final Date beginDate = formatStringToDate(begin);
            result = getAvailableRooms(
                    connection,
                    beginDate,
                    BookingHandler.parseDuration(duration, beginDate),
                    result
            );
        }

        if (!request.getParametersByName(capacityParameter).isEmpty()) {
            final int capacity = Integer.parseInt(request.getParametersByName(capacityParameter).get(0));
            result.removeIf(room -> room.getCapacity() < capacity);
        }
        if (!request.getParametersByName(labelParameter).isEmpty()) {
            List<String> labels = request.getParametersByName(labelParameter);
            for (String labelName : labels) {
                result.removeIf(room -> room.getLabels()
                        .stream().noneMatch(label -> label.getName().equals(labelName)));
            }
        }
        return result;
    }
}

