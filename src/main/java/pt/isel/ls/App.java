package pt.isel.ls;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.Model;
import pt.isel.ls.handler.booking.delete.DeleteBooking;
import pt.isel.ls.handler.booking.delete.DeleteBookingView;
import pt.isel.ls.handler.booking.getall.GetBooking;
import pt.isel.ls.handler.booking.getall.GetBookingView;
import pt.isel.ls.handler.booking.getbyid.GetBookingById;
import pt.isel.ls.handler.booking.getbyid.GetBookingByIdView;
import pt.isel.ls.handler.booking.getbyowner.GetBookingByOwner;
import pt.isel.ls.handler.booking.getbyowner.GetBookingByOwnerView;
import pt.isel.ls.handler.booking.getbyroom.GetBookingByRoom;
import pt.isel.ls.handler.booking.getbyroom.GetBookingByRoomView;
import pt.isel.ls.handler.booking.post.PostBooking;
import pt.isel.ls.handler.booking.post.PostBookingView;
import pt.isel.ls.handler.booking.put.PutBooking;
import pt.isel.ls.handler.booking.put.PutBookingView;
import pt.isel.ls.handler.exit.Exit;
import pt.isel.ls.handler.exit.ExitView;
import pt.isel.ls.handler.index.Index;
import pt.isel.ls.handler.index.IndexView;
import pt.isel.ls.handler.label.getall.GetLabel;
import pt.isel.ls.handler.label.getall.GetLabelView;
import pt.isel.ls.handler.label.getbyid.GetLabelById;
import pt.isel.ls.handler.label.getbyid.GetLabelByIdView;
import pt.isel.ls.handler.label.post.PostLabel;
import pt.isel.ls.handler.label.post.PostLabelView;
import pt.isel.ls.handler.listen.HttpServlet;
import pt.isel.ls.handler.listen.HttpServletView;
import pt.isel.ls.handler.option.Option;
import pt.isel.ls.handler.option.OptionView;
import pt.isel.ls.handler.result.View;
import pt.isel.ls.handler.room.getall.GetRoom;
import pt.isel.ls.handler.room.getall.GetRoomView;
import pt.isel.ls.handler.room.getbyid.GetRoomById;
import pt.isel.ls.handler.room.getbyid.GetRoomByIdView;
import pt.isel.ls.handler.room.getbylabel.GetRoomByLabelView;
import pt.isel.ls.handler.room.getbylabel.GetRoomsByLabel;
import pt.isel.ls.handler.room.post.PostRoom;
import pt.isel.ls.handler.room.post.PostRoomView;
import pt.isel.ls.handler.room.search.SearchRoomView;
import pt.isel.ls.handler.time.Time;
import pt.isel.ls.handler.time.TimeView;
import pt.isel.ls.handler.user.getall.GetUser;
import pt.isel.ls.handler.user.getall.GetUserView;
import pt.isel.ls.handler.user.getbyid.GetUserById;
import pt.isel.ls.handler.user.getbyid.GetUserByIdView;
import pt.isel.ls.handler.user.post.PostUser;
import pt.isel.ls.handler.user.post.PostUserView;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.utils.UtilMethods;

import java.util.Scanner;

import static pt.isel.ls.CommandUri.*;


public class App {

    public static CommandRouter commandRouter;

    public static void main(String[] args) {
        LocalInterface local = new LocalInterface();
        String[] rawTask;
        commandRouter = initCommandRouterBehaviour();
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

        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));

        CommandHandler foundHandler = routeResult.getCommandPackage().handler;
        View foundView = routeResult.getCommandPackage().view;
        Model resultModel = foundHandler.execute(userRequest);
        foundView.setModel(resultModel);
        outputInterface.show(foundView, userRequest.getHeader());

    }

    public static void initCommandRoutes(CommandRouter commandRouter) {
        commandRouter
                .addRoute(
                        POST_ROOMS_URL, new CommandPackage(new PostRoom(), new PostRoomView()));
        commandRouter
                .addRoute(
                        GET_ROOMS_URL, new CommandPackage(new GetRoom(), new GetRoomView()));
        commandRouter
                .addRoute(
                        GET_SEARCH_ROOM_URL, new CommandPackage(new GetLabel(), new SearchRoomView()));
        commandRouter
                .addRoute(
                        GET_ROOM_BY_ID_URL, new CommandPackage(new GetRoomById(), new GetRoomByIdView()));
        commandRouter
                .addRoute(
                        POST_BOOKING_URL, new CommandPackage(new PostBooking(), new PostBookingView()));
        commandRouter
                .addRoute(
                        GET_BOOKING_BY_ROOM_URL, new CommandPackage(
                                new GetBookingByRoom(), new GetBookingByRoomView()));
        commandRouter
                .addRoute(
                        PUT_BOOKING_URL, new CommandPackage(new PutBooking(), new PutBookingView()));
        commandRouter
                .addRoute(
                        DELETE_BOOKING_URL, new CommandPackage(new DeleteBooking(), new DeleteBookingView()));
        commandRouter
                .addRoute(
                        GET_BOOKING_BY_ID_URL, new CommandPackage(new GetBookingById(), new GetBookingByIdView()));
        commandRouter
                .addRoute(
                        POST_USER_URL, new CommandPackage(new PostUser(), new PostUserView()));
        commandRouter
                .addRoute(
                        GET_USER_URL, new CommandPackage(new GetUser(), new GetUserView()));
        commandRouter
                .addRoute(
                        GET_USER_BY_ID_URL, new CommandPackage(new GetUserById(), new GetUserByIdView()));
        commandRouter
                .addRoute(
                        GET_BOOKING_BY_OWNER, new CommandPackage(new GetBookingByOwner(), new GetBookingByOwnerView()));
        commandRouter
                .addRoute(
                        GET_BOOKING_URL, new CommandPackage(new GetBooking(), new GetBookingView()));
        commandRouter
                .addRoute(
                        POST_LABEL_URL, new CommandPackage(new PostLabel(), new PostLabelView()));
        commandRouter
                .addRoute(
                        GET_LABEL_URL, new CommandPackage(new GetLabel(), new GetLabelView()));
        commandRouter
                .addRoute(
                        GET_LABEL_BY_ID_URL, new CommandPackage(new GetLabelById(), new GetLabelByIdView()));
        commandRouter
                .addRoute(
                        GET_ROOM_BY_LABEL_URL, new CommandPackage(new GetRoomsByLabel(), new GetRoomByLabelView()));
        commandRouter
                .addRoute(
                        EXIT_URL, new CommandPackage(new Exit(), new ExitView()));
        commandRouter
                .addRoute(
                        TIME_URL, new CommandPackage(new Time(), new TimeView()));
        commandRouter
                .addRoute(
                        OPTION_URL, new CommandPackage(new Option(), new OptionView()));
        commandRouter
                .addRoute(
                        INDEX_URL, new CommandPackage(new Index(), new IndexView()));
        commandRouter
                .addRoute(
                        LISTEN_URL, new CommandPackage(new HttpServlet(), new HttpServletView()));
    }

    public static CommandRouter initCommandRouterBehaviour() {
        CommandRouter commandRouter = new CommandRouter();
        App.initCommandRoutes(commandRouter);
        return commandRouter;
    }

}


