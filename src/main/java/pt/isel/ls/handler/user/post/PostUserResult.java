package pt.isel.ls.handler.user.post;

import pt.isel.ls.handler.ResponseHeader;
import pt.isel.ls.handler.Result;
import pt.isel.ls.http.StatusCode;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.fixPath;

public class PostUserResult extends Result {
    private final String model;
    private final String redirectPath;

    public PostUserResult(String userEmail, String redirectPath) {
        this.model = userEmail;
        this.redirectPath = fixPath(redirectPath);
    }


    @Override
    public String name() {
        return model + " user posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("user id: " + model))

        ).build();
    }

    @Override
    public String plainOutput() {
        return "User Created : " + model;
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
