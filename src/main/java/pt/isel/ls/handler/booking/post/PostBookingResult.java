package pt.isel.ls.handler.booking.post;

import pt.isel.ls.handler.ResponseHeader;
import pt.isel.ls.handler.Result;
import pt.isel.ls.http.StatusCode;

import static pt.isel.ls.userinterfaces.format.html.htmlemitter.Element.*;
import static pt.isel.ls.utils.UtilMethods.fixPath;

public class PostBookingResult extends Result {
    private final String bookingId;
    private final String path;

    public PostBookingResult(String postedBookingId, String path) {
        this.bookingId = postedBookingId;
        this.path = fixPath(path);
    }

    @Override
    public String name() {
        return bookingId + " booking posted";
    }

    @Override
    public String htmlOutput() {
        return html(
                head(
                        title(text(name()))
                ),
                body(
                        h1(text(name())),
                        text("booking id: " + bookingId))

        ).build();
    }

    @Override
    public String plainOutput() {
        return "Booking Created : " + bookingId;
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
