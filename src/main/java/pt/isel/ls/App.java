package pt.isel.ls;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.ResultView;
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
import pt.isel.ls.handler.listen.HttpServlet;
import pt.isel.ls.handler.option.Option;
import pt.isel.ls.handler.room.getall.GetRoom;
import pt.isel.ls.handler.room.getbyid.GetRoomById;
import pt.isel.ls.handler.room.getbylabel.GetRoomsByLabel;
import pt.isel.ls.handler.room.post.PostRoom;
import pt.isel.ls.handler.room.search.SearchRoom;
import pt.isel.ls.handler.time.Time;
import pt.isel.ls.handler.user.getall.GetUser;
import pt.isel.ls.handler.user.getbyid.GetUserById;
import pt.isel.ls.handler.user.post.PostUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.request.Template;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class App {
    public static Router router;

    public static void main(String[] args) {
        router = new Router();
        LocalInterface local = new LocalInterface();
        String[] rawTask;
        initRoutes(router);
        if (args.length > 0) {
            rawTask = args;
            try {

                executeTask(router, local, rawTask);
            } catch (NoSuchMethodException e) {
                local.showError(e.getMessage());
            }
        } else {
            while (true) {
                local.askForCommand();
                rawTask = new Scanner(System.in).nextLine().split(" ");
                try {
                    executeTask(router, local, rawTask);
                } catch (NoSuchMethodException e) {
                    local.showError(e.getMessage());
                }
            }
        }
    }

    public static void executeTask(Router router, OutputInterface outputInterface, String[] rawTask)
            throws NoSuchMethodException {
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, outputInterface);
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());

        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
        Connection connection = null;
        ResultView resultView = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            resultView = routeResult.getHandler().execute(userRequest, connection);
            connection.commit();
        } catch (Exception e) {
            try {
                assert connection != null;
                connection.rollback();
                outputInterface.showError(e.getMessage());
            } catch (SQLException ex) {
                outputInterface.showError(ex.getMessage());
            }
            outputInterface.showError(e.getMessage());
        } finally {
            assert connection != null;
            try {
                connection.close();
            } catch (SQLException e) {
                outputInterface.showError(e.getMessage());
            }
        }
        try {
            outputInterface.show(resultView, userRequest.getHeader()); //SUCCESS!
        } catch (IOException e) {
            outputInterface.showError(e.getMessage());
        }
    }

    public static void initRoutes(Router router) {
        router.addRoute(Method.POST, new PathTemplate(Template.ROOMS), new PostRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS), new GetRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_SEARCH), new SearchRoom());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID), new GetRoomById());
        router.addRoute(Method.POST, new PathTemplate(Template.ROOMS_RID_BOOKINGS), new PostBooking());
        router.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID_BOOKINGS), new GetBookingByRoom());
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
        router.addRoute(Method.GET, new PathTemplate(Template.LABELS_LID), new GetLabelById());
        router.addRoute(Method.GET, new PathTemplate(Template.LABELS_LID_ROOMS), new GetRoomsByLabel());
        router.addRoute(Method.EXIT, new PathTemplate(Template.SLASH), new Exit());
        router.addRoute(Method.GET, new PathTemplate(Template.TIME), new Time());
        router.addRoute(Method.OPTIONS, new PathTemplate(Template.SLASH), new Option());
        router.addRoute(Method.GET, new PathTemplate(Template.SLASH), new Index());
        router.addRoute(Method.LISTEN, new PathTemplate(Template.SLASH), new HttpServlet());
    }
}


