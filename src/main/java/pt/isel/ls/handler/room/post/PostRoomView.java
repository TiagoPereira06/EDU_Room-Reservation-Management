package pt.isel.ls.handler.room.post;

import pt.isel.ls.handler.ResultView;

public class PostRoomView implements ResultView {
    private final String model;

    public PostRoomView(String roomId) {
        this.model = roomId;
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
