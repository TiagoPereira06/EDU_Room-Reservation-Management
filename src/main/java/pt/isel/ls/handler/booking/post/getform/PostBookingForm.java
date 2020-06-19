package pt.isel.ls.handler.booking.post.getform;

import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.handler.user.UserHandler;
import pt.isel.ls.request.CommandRequest;

public class PostBookingForm implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        return commandRequest.transactionManager.execute(connection ->
                new PostBookingFormResult(
                        UserHandler.getAllUsers(connection),
                        commandRequest.getPostParameters(),
                        commandRequest.getParameterByName(BookingHandler.roomIdArgument)
                ));
    }

    @Override
    public String description() {
        return "Returns Post Booking Form";
    }
}
