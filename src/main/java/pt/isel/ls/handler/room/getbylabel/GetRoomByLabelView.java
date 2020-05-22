package pt.isel.ls.handler.room.getbylabel;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.model.Room;

import java.util.List;

public class GetRoomByLabelView implements ResultView {
    private final List<Room> model;

    public GetRoomByLabelView(List<Room> roomsByLabel) {
        this.model = roomsByLabel;
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
