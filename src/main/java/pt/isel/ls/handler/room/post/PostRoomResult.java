package pt.isel.ls.handler.room.post;

import pt.isel.ls.handler.ResponseHeader;
import pt.isel.ls.handler.Result;
import pt.isel.ls.http.StatusCode;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;

public class PostRoomResult extends Result {
    private final String model;
    private final String redirectPath;

    public PostRoomResult(String roomName, String redirectPath) {
        this.model = roomName;
        this.redirectPath = redirectPath.replace(" ", "+");
    }

    @Override
    public String name() {
        return model + " Room Created";
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
        return "Room Created : " + model;
    }

    @Override
    public StatusCode successStatusCode() {
        return StatusCode.SeeOther;
    }

    @Override
    public ResponseHeader responseHeader() {
        return new ResponseHeader("Location", redirectPath);
    }
}

