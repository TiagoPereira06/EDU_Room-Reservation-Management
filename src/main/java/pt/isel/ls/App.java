package pt.isel.ls;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.Exit;
import pt.isel.ls.handler.booking.GetBooking;
import pt.isel.ls.handler.booking.GetBookingById;
import pt.isel.ls.handler.booking.GetBookingByOwner;
import pt.isel.ls.handler.booking.PostBooking;
import pt.isel.ls.handler.label.GetLabel;
import pt.isel.ls.handler.label.PostLabel;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.handler.room.GetRoomById;
import pt.isel.ls.handler.room.GetRoomsByLabel;
import pt.isel.ls.handler.room.PostRoom;
import pt.isel.ls.handler.user.GetUser;
import pt.isel.ls.handler.user.GetUserById;
import pt.isel.ls.handler.user.PostUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.request.Template;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Router router = new Router();
        UserInterface ui = new UserInterface();
        String[] rawTask;
        initRoutes(router);
        if (args.length > 0) {
            rawTask = args;
            try {
                executeTask(router, ui, rawTask);
            } catch (NoSuchMethodException e) {
                ui.show("ERROR ! : ".concat(e.getMessage()));
            }
        } else {
            while (true) {
                ui.askForCommand();
                rawTask = new Scanner(System.in).nextLine().split(" ");
                try {
                    executeTask(router, ui, rawTask);
                } catch (NoSuchMethodException e) {
                    ui.show("ERROR -> ".concat(e.getMessage()));
                }
            }
        }
    }

    private static void executeTask(Router router, UserInterface ui, String[] rawTask) throws NoSuchMethodException {
        CommandRequest userRequest;
        final Method method;
        try {
            method = Method.valueOf(rawTask[0]);
        } catch (IllegalArgumentException e) {
            throw new NoSuchMethodException("Request Not Found");
        }
        userRequest = new CommandRequest(method, new Path(rawTask[1]),
                UtilMethods.getParameters(rawTask));
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
        Connection connection = null;
        CommandResult commandResult = new CommandResult();
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            commandResult = routeResult.getHandler().execute(userRequest, connection);
            connection.commit();
        } catch (SQLException | ParseException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                commandResult.getResult().add("ERROR -> ".concat(ex.getMessage()));
            }
            commandResult.getResult().add("ERROR -> ".concat(e.getMessage()));
        } finally {
            assert connection != null;
            try {
                connection.close();
            } catch (SQLException e) {
                commandResult.getResult().add("ERROR -> ".concat(e.getMessage()));
            }
        }
        ui.show(commandResult);
    }

    public static void initRoutes(Router router) {
        router.addRoute(Method.POST, new PathTemplate(Template.ROOMS), new PostRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS), new GetRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID), new GetRoomById());
        router.addRoute(Method.POST, new PathTemplate(Template.BOOKINGS), new PostBooking());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID), new GetBookingById());
        router.addRoute(Method.POST, new PathTemplate(Template.USERS), new PostUser());
        router.addRoute(Method.GET, new PathTemplate(Template.USERS), new GetUser());
        router.addRoute(Method.GET, new PathTemplate(Template.USERS_UID), new GetUserById());
        router.addRoute(Method.GET, new PathTemplate(Template.USERS_UID_BOOKINGS), new GetBookingByOwner());
        router.addRoute(Method.GET, new PathTemplate(Template.BOOKINGS), new GetBooking());
        router.addRoute(Method.POST, new PathTemplate(Template.LABELS), new PostLabel());
        router.addRoute(Method.GET, new PathTemplate(Template.LABELS), new GetLabel());
        router.addRoute(Method.GET, new PathTemplate(Template.LABELS_LID_ROOMS), new GetRoomsByLabel());
        router.addRoute(Method.EXIT, new PathTemplate(Template.OPTION), new Exit());
    }
}


