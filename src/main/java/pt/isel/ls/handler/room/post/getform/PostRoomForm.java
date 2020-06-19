package pt.isel.ls.handler.room.post.getform;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.request.CommandRequest;

public class PostRoomForm implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute((connection) ->
                new PostRoomFormResult(
                        LabelHandler.getAllLabels(connection),
                        commandRequest.getPostParameters())
        );
    }

    @Override
    public String description() {
        return null;
    }
}
