package pt.isel.ls.handler.room.post;

import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.result.View;

import static pt.isel.ls.handler.result.html.Element.*;

public class PostRoomView extends View {
    private String model;

    public PostRoomView(Object roomId) {
        this.model = (String) roomId;
    }

    public PostRoomView() {
    }

    @Override
    public String name() {
        return model + " room posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("room id: " + model))

        ).build();
    }

    @Override
    public String plainOutput() {
        return model;
    }

    @Override
    public void setModel(Model resultModel) {
        this.model = (String) resultModel.getPrimaryData();
    }

}

