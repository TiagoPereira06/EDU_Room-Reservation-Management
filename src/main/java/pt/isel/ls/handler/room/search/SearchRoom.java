package pt.isel.ls.handler.room.search;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.label.LabelHandler;
import pt.isel.ls.request.CommandRequest;

public class SearchRoom implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) ->
                new SearchRoomResult(
                        LabelHandler.getAllLabels(connection))
        );
    }

    @Override
    public String description() {
        return null;
    }
}
