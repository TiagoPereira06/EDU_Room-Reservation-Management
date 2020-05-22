package pt.isel.ls.handler.room.post;

import pt.isel.ls.handler.result.View;

public class PostRoomView extends View {
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
