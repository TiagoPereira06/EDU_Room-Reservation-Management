package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Template;


public interface CommandHandler {

    CommandResult execute(CommandRequest commandRequest);

    Template getTemplate();

    String description();


}
