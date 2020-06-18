package pt.isel.ls.routertests;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.errors.router.RouterException;
import pt.isel.ls.userinterfaces.interfaces.LocalInterface;
import pt.isel.ls.handler.booking.getall.GetBooking;
import pt.isel.ls.handler.label.getall.GetLabel;
import pt.isel.ls.handler.room.getall.GetRoom;
import pt.isel.ls.handler.user.getall.GetUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.utils.UtilMethods;


public class RouterGetsTests {
    private final CommandRouter commandRouter;

    public RouterGetsTests() {
        commandRouter = new CommandRouter();
        App.initCommandRoutes(commandRouter);
    }

    @Test
    public void routerGetUsers() throws RouterException {
        String[] rawTask = {"GET", "/users"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetUser);
    }

    @Test
    public void routerGetLabels() throws RouterException {
        String[] rawTask = {"GET", "/labels"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetLabel);
    }

    @Test
    public void routerGetBookings() throws RouterException {
        String[] rawTask = {"GET", "/bookings"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetBooking);
    }

    @Test
    public void routerGetRooms() throws RouterException {
        String[] rawTask = {"GET", "/rooms"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetRoom);
    }

    @Test
    public void routerGetRoomsWithCapacity() throws RouterException {
        String[] rawTask = {"GET", "/rooms", "capacity=25"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetRoom);

    }

    @Test
    public void routerGetRoomsWithLabel() throws RouterException {
        String[] rawTask = {"GET", "/rooms", "label=windows"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetRoom);

    }

    @Test
    public void routerGetRoomsWithTime() throws RouterException {
        String[] rawTask = {"GET", "/rooms", "begin=2020-04-22+12:00:00"
                + "&duration=60"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof GetRoom);

    }

}
