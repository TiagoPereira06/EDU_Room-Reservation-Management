package pt.isel.ls.handler.room.search;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.room.RoomHandler;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

public class SearchRoom extends RoomHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) {
        return new SearchRoomView();
    }

    @Override
    public String description() {
        return null;
    }
}
