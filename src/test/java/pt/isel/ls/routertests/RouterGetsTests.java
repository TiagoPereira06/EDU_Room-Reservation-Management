package pt.isel.ls.routertests;

import junit.framework.Assert;
import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.handler.booking.GetBooking;
import pt.isel.ls.handler.label.GetLabel;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.handler.user.GetUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

public class RouterGetsTests {
    private Router router;

    public RouterGetsTests() {
        router = new Router();
        App.initRoutes(router);
    }

    @Test
    public void routerGetUsers() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/users"};
        CommandRequest userRequest;
        userRequest = new CommandRequest(Method.valueOf(rawTask[0]), new Path(rawTask[1]),
                UtilMethods.getParameters(rawTask));
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetUser);
    }

    @Test
    public void routerGetLabels() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/labels"};
        CommandRequest userRequest;
        userRequest = new CommandRequest(Method.valueOf(rawTask[0]), new Path(rawTask[1]),
                UtilMethods.getParameters(rawTask));
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetLabel);
    }

    @Test
    public void routerGetBookings() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/bookings"};
        CommandRequest userRequest;
        userRequest = new CommandRequest(Method.valueOf(rawTask[0]), new Path(rawTask[1]),
                UtilMethods.getParameters(rawTask));
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetBooking);
    }

    @Test
    public void routerGetRooms() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/rooms"};
        CommandRequest userRequest;
        userRequest = new CommandRequest(Method.valueOf(rawTask[0]), new Path(rawTask[1]),
                UtilMethods.getParameters(rawTask));
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof GetRoom);
    }
}
