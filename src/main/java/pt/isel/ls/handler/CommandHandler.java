package pt.isel.ls.handler;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.request.CommandRequest;


public interface CommandHandler {

    CommandResult execute(CommandRequest commandRequest) throws CommandException;

    String description();

}
