package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;


public interface CommandHandler {

    ResultView execute(CommandRequest commandRequest) throws Exception;

    String description();

}
