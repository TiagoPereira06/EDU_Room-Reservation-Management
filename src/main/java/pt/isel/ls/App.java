package pt.isel.ls;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.delete.DeleteBooking;
import pt.isel.ls.handler.booking.getall.GetBooking;
import pt.isel.ls.handler.booking.getbyid.GetBookingById;
import pt.isel.ls.handler.booking.getbyowner.GetBookingByOwner;
import pt.isel.ls.handler.booking.getbyroom.GetBookingByRoom;
import pt.isel.ls.handler.booking.post.PostBooking;
import pt.isel.ls.handler.booking.put.PutBooking;
import pt.isel.ls.handler.exit.Exit;
import pt.isel.ls.handler.index.Index;
import pt.isel.ls.handler.label.getall.GetLabel;
import pt.isel.ls.handler.label.getbyid.GetLabelById;
import pt.isel.ls.handler.label.post.PostLabel;
import pt.isel.ls.handler.label.post.getform.PostLabelForm;
import pt.isel.ls.handler.listen.HttpServlet;
import pt.isel.ls.handler.option.Option;
import pt.isel.ls.handler.room.getall.GetRoom;
import pt.isel.ls.handler.room.getbyid.GetRoomById;
import pt.isel.ls.handler.room.getbylabel.GetRoomsByLabel;
import pt.isel.ls.handler.room.post.PostRoom;
import pt.isel.ls.handler.room.post.getform.PostRoomForm;
import pt.isel.ls.handler.room.search.SearchRoom;
import pt.isel.ls.handler.time.Time;
import pt.isel.ls.handler.user.getall.GetUser;
import pt.isel.ls.handler.user.getbyid.GetUserById;
import pt.isel.ls.handler.user.post.PostUser;
import pt.isel.ls.handler.user.post.getform.PostUserForm;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.userinterfaces.interfaces.LocalInterface;
import pt.isel.ls.userinterfaces.interfaces.OutputInterface;
import pt.isel.ls.utils.UtilMethods;

import java.util.List;
import java.util.Scanner;

import static pt.isel.ls.CommandUri.*;


public class App {

    public static CommandRouter commandRouter;

    public static void main(String[] args) {
        LocalInterface local = new LocalInterface();
        String[] rawTask;
        commandRouter = initRouterBehaviour();
        if (args.length > 0) {
            rawTask = args;
            try {
                executeTask(commandRouter, local, rawTask);
            } catch (Exception e) {
                local.showError(e);
            }
        } else {
            while (true) {
                local.askForCommand();
                rawTask = new Scanner(System.in).nextLine().split(" ");
                try {
                    executeTask(commandRouter, local, rawTask);

                } catch (Exception e) {
                    local.showError(e);
                }
            }
        }
    }


    public static void executeTask(CommandRouter commandRouter, OutputInterface outputInterface, String[] rawTask)
            throws Exception {
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, outputInterface);
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());

        final List<Parameter> parameterList =
                UtilMethods.concatTwoLists(
                        routeResult.getParameters(),
                        userRequest.getParameter()
                );
        userRequest.setParameter(parameterList);

        CommandHandler foundHandler = routeResult.getCommandHandler();
        CommandResult commandResult = foundHandler.execute(userRequest);
        outputInterface.show(commandResult, userRequest.getHeader());

    }

    public static void initCommandRoutes(CommandRouter commandRouter) {
        commandRouter
                .addRoute(
                        POST_ROOMS_URL, new PostRoom());
        commandRouter
                .addRoute(
                        GET_POST_ROOM_FORM_URL, new PostRoomForm());
        commandRouter
                .addRoute(
                        GET_POST_LABEL_FORM_URL, new PostLabelForm());
        commandRouter
                .addRoute(
                        GET_POST_USER_FORM_URL, new PostUserForm());
        commandRouter
                .addRoute(
                        GET_SEARCH_ROOM_URL, new SearchRoom());
        commandRouter
                .addRoute(
                        GET_ROOMS_URL, new GetRoom());
        commandRouter
                .addRoute(
                        GET_ROOM_BY_ID_URL, new GetRoomById());
        commandRouter
                .addRoute(
                        POST_BOOKING_URL, new PostBooking());
        commandRouter
                .addRoute(
                        GET_BOOKING_BY_ROOM_URL, new GetBookingByRoom());
        commandRouter
                .addRoute(
                        PUT_BOOKING_URL, new PutBooking());
        commandRouter
                .addRoute(
                        DELETE_BOOKING_URL, new DeleteBooking());
        commandRouter
                .addRoute(
                        GET_BOOKING_BY_ID_URL, new GetBookingById());
        commandRouter
                .addRoute(
                        POST_USER_URL, new PostUser());
        commandRouter
                .addRoute(
                        GET_USER_URL, new GetUser());
        commandRouter
                .addRoute(
                        GET_USER_BY_ID_URL, new GetUserById());
        commandRouter
                .addRoute(
                        GET_BOOKING_BY_OWNER, new GetBookingByOwner());
        commandRouter
                .addRoute(
                        GET_BOOKING_URL, new GetBooking());
        commandRouter
                .addRoute(
                        POST_LABEL_URL, new PostLabel());
        commandRouter
                .addRoute(
                        GET_LABEL_URL, new GetLabel());
        commandRouter
                .addRoute(
                        GET_LABEL_BY_ID_URL, new GetLabelById());
        commandRouter
                .addRoute(
                        GET_ROOM_BY_LABEL_URL, new GetRoomsByLabel());
        commandRouter
                .addRoute(
                        EXIT_URL, new Exit());
        commandRouter
                .addRoute(
                        TIME_URL, new Time());
        commandRouter
                .addRoute(
                        OPTION_URL, new Option());
        commandRouter
                .addRoute(
                        INDEX_URL, new Index());
        commandRouter
                .addRoute(
                        LISTEN_URL, new HttpServlet());
    }

    public static CommandRouter initRouterBehaviour() {
        CommandRouter commandRouter = new CommandRouter();
        App.initCommandRoutes(commandRouter);
        return commandRouter;
    }

}


