package pt.isel.ls.handler.room.getall;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.errors.command.InvalidArgumentException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.model.Room;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static pt.isel.ls.utils.UtilMethods.formatStringToDate;

public class GetRoom extends RoomHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) ->
                new GetRoomResult(executeGetCommand(commandRequest, connection)));
    }

    @Override
    public String description() {
        return "Show all rooms";
    }

    private List<Room> executeGetCommand(CommandRequest request, Connection connection)
            throws InternalDataBaseException, InvalidArgumentException {
        List<Room> result = getAllRoomsWithLabels(connection);

        if (request.getParameterByName(beginParameter) != null
                && request.getParameterByName(durationParameter) != null) {
            final String begin = request.getParameterByName(beginParameter);
            final String duration = request.getParameterByName(durationParameter);
            final Date beginDate;
            try {
                beginDate = formatStringToDate(begin);
            } catch (ParseException e) {
                throw new InvalidArgumentException(e.getMessage());
            }
            result = getAvailableRooms(
                    connection,
                    beginDate,
                    BookingHandler.parseDuration(duration, beginDate),
                    result
            );
        }

        if (request.getParameterByName(capacityParameter) != null) {
            final int capacity = Integer.parseInt(request.getParameterByName(capacityParameter));
            result.removeIf(room -> room.getCapacity() < capacity);
        }
        if (request.getParameterByName(labelParameter) != null) {
            List<String> labels = request.getParametersByName(labelParameter);
            for (String labelName : labels) {
                result.removeIf(room -> room.getLabels()
                        .stream().noneMatch(label -> label.getName().equals(labelName)));
            }
        }
        return result;
    }
}

