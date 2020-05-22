package pt.isel.ls.handler.room.getbyid;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.model.Room;

public class GetRoomByIdView implements ResultView {
    private final Room model;

    public GetRoomByIdView(Room roomById) {
        this.model = roomById;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String htmlOutput() {
        return null;
    }

    @Override
    public String plainOutput() {
        return null;
    }
}
