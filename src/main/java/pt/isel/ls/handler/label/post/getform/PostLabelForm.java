package pt.isel.ls.handler.label.post.getform;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;

public class PostLabelForm implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return new PostLabelFormResult(commandRequest.getPostParameters());
    }

    @Override
    public String description() {
        return "Returns Post Label Form";
    }
}
