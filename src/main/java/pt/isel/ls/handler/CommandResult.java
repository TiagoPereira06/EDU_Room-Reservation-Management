package pt.isel.ls.handler;

import pt.isel.ls.http.StatusCode;

public interface CommandResult {

    String name();

    String htmlOutput();

    String plainOutput();

    StatusCode successStatusCode();

    ResponseHeader responseHeader();
}
