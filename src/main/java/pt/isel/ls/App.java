package pt.isel.ls;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.Exit;
import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.handler.booking.GetBooking;
import pt.isel.ls.handler.booking.GetBookingById;
import pt.isel.ls.handler.booking.GetBookingByOwner;
import pt.isel.ls.handler.booking.PostBooking;
import pt.isel.ls.handler.booking.PutBooking;
import pt.isel.ls.handler.booking.DeleteBooking;
import pt.isel.ls.handler.label.GetLabel;
import pt.isel.ls.handler.label.PostLabel;
import pt.isel.ls.handler.listener.Listen;
import pt.isel.ls.handler.option.Option;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.handler.room.GetRoomById;
import pt.isel.ls.handler.room.GetRoomsByLabel;
import pt.isel.ls.handler.room.PostRoom;
import pt.isel.ls.handler.time.Time;
import pt.isel.ls.handler.user.GetUser;
import pt.isel.ls.handler.user.GetUserById;
import pt.isel.ls.handler.user.PostUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.request.Template;
import pt.isel.ls.request.Header;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.HeaderType;
import pt.isel.ls.request.HeaderValue;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;


public class App {
    public static Router router;

    public static void main(String[] args) {
        router = new Router();
        UserInterface ui = new UserInterface();
        String[] rawTask;
        initRoutes(router);
        if (args.length > 0) {
            rawTask = args;
            try {
                executeTask(router, ui, rawTask);
            } catch (NoSuchMethodException e) {
                ui.showError(e.getMessage());
            }
        } else {
            while (true) {
                ui.askForCommand();
                rawTask = new Scanner(System.in).nextLine().split(" ");
                try {
                    executeTask(router, ui, rawTask);
                } catch (NoSuchMethodException e) {
                    ui.showError(e.getMessage());
                }
            }
        }
    }

    private static void executeTask(Router router, UserInterface ui, String[] rawTask) throws NoSuchMethodException {

        CommandRequest userRequest = formatUserInput(rawTask);

        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());

        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
        Connection connection = null;
        ResultInterface resultInterface = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            resultInterface = routeResult.getHandler().execute(userRequest, connection);
            connection.commit();
        } catch (SQLException | ParseException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                ui.showError(ex.getMessage());
            }
            ui.showError(e.getMessage());
        } finally {
            assert connection != null;
            try {
                connection.close();
            } catch (SQLException e) {
                ui.showError(e.getMessage());
            }
        }
        try {
            ui.show(resultInterface, userRequest.getHeader());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public static CommandRequest formatUserInput(String[] rawTask) throws NoSuchMethodException {
        final Method method;
        final Path path;
        Header header;
        List<Parameter> parameters;
        try {
            method = Method.valueOf(rawTask[0]);
        } catch (IllegalArgumentException e) {
            throw new NoSuchMethodException("Request Not Found");
        }
        path = new Path(rawTask[1]);
        //OS HEADERS SÓ APARECEM NA POS 2!
        try {
            header = checkHeader(rawTask, 2); //EXISTE HEADER
            parameters = UtilMethods.getParameters(rawTask, 3); //PARAM OU NÃO EXISTE OU NA POS3
        } catch (IllegalArgumentException e) {
            header = new Header();
            header.addPair(HeaderType.ACCEPT, new HeaderValue("text/plain")); //NÃO EXISTE HEADER
            parameters = UtilMethods.getParameters(rawTask, 2); //PARAM OU NÃO EXISTE OU NA POS2
        }
        return new CommandRequest(method, path, parameters, header);
    }

    private static Header checkHeader(String[] rawInput, int index) throws IllegalArgumentException {
        if (index >= rawInput.length) {
            throw new IllegalArgumentException();
        }
        final Header header = new Header();
        String s = rawInput[index];
        String[] parts = s.split("\\|");
        for (String str : parts) {
            String[] pair = str.split(":");
            HeaderType type = HeaderType.valueOf(pair[0].toUpperCase().replace("-", ""));
            HeaderValue value = new HeaderValue(pair[1]);
            header.addPair(type, value);
        }
        return header;
    }

    public static void initRoutes(Router router) {
        router.addRoute(Method.POST, new PathTemplate(Template.ROOMS), new PostRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS), new GetRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID), new GetRoomById());
        router.addRoute(Method.POST, new PathTemplate(Template.ROOMS_RID_BOOKINGS), new PostBooking());
        router.addRoute(Method.PUT, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID), new PutBooking());
        router.addRoute(Method.DELETE, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID), new DeleteBooking());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID), new GetBookingById());
        router.addRoute(Method.POST, new PathTemplate(Template.USERS), new PostUser());
        router.addRoute(Method.GET, new PathTemplate(Template.USERS), new GetUser());
        router.addRoute(Method.GET, new PathTemplate(Template.USERS_UID), new GetUserById());
        router.addRoute(Method.GET, new PathTemplate(Template.USERS_UID_BOOKINGS), new GetBookingByOwner());
        router.addRoute(Method.GET, new PathTemplate(Template.BOOKINGS), new GetBooking());
        router.addRoute(Method.POST, new PathTemplate(Template.LABELS), new PostLabel());
        router.addRoute(Method.GET, new PathTemplate(Template.LABELS), new GetLabel());
        router.addRoute(Method.GET, new PathTemplate(Template.LABELS_LID_ROOMS), new GetRoomsByLabel());
        router.addRoute(Method.EXIT, new PathTemplate(Template.SLASH), new Exit());
        router.addRoute(Method.GET, new PathTemplate(Template.TIME), new Time());
        router.addRoute(Method.OPTION, new PathTemplate(Template.SLASH), new Option());
        router.addRoute(Method.LISTEN, new PathTemplate(Template.SLASH), new Listen());
    }
}


