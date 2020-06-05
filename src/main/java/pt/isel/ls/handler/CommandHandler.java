package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;


public interface CommandHandler {

    Model execute(CommandRequest commandRequest) throws Exception;

    String description();

}
