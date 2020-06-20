package pt.isel.ls.handler.label.post;

import pt.isel.ls.handler.ResponseHeader;
import pt.isel.ls.handler.Result;
import pt.isel.ls.http.StatusCode;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.fixPath;

public class PostLabelResult extends Result {
    private final String postedLabelName;
    private final String path;

    public PostLabelResult(String postedLabelName, String redirectPath) {
        this.postedLabelName = postedLabelName;
        this.path = fixPath(redirectPath);
    }

    @Override
    public String name() {
        return postedLabelName + " Label Created";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("label id: " + postedLabelName))

        ).build();
    }

    @Override
    public String plainOutput() {
        return "Label Created : " + postedLabelName;
    }

    @Override
    public StatusCode successStatusCode() {
        return StatusCode.SeeOther;
    }

    @Override
    public ResponseHeader responseHeader() {
        return new ResponseHeader("Location", path);
    }

}
