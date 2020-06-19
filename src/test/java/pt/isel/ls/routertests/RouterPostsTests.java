package pt.isel.ls.routertests;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.errors.router.RouterException;
import pt.isel.ls.userinterfaces.interfaces.LocalInterface;
import pt.isel.ls.handler.booking.post.PostBooking;
import pt.isel.ls.handler.label.post.PostLabel;
import pt.isel.ls.handler.room.post.PostRoom;
import pt.isel.ls.handler.user.post.PostUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.utils.UtilMethods;

public class RouterPostsTests {
    private final CommandRouter commandRouter;

    public RouterPostsTests() {
        commandRouter = new CommandRouter();
        App.initCommandRoutes(commandRouter);
    }

    @Test
    public void routerPostUser() throws RouterException {
        String[] rawTask = {"POST", "/users/create", "name=Haris+Seferovic&email=haris@slb.pt"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof PostUser);
    }

    @Test
    public void routerPostLabel() throws RouterException {
        String[] rawTask = {"POST", "/labels/create", "name=slow+internet"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof PostLabel);
    }

    @Test
    public void routerPostRoom() throws RouterException {
        String[] rawTask = {"POST", "/rooms/create", "name=LGO"
                + "&description=muitobom"
                + "&location=Building+F+floor+-1"
                + "&capacity=55"
                + "&label=monitors&label=windows"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof PostRoom);
    }

    @Test
    public void routerPostBooking() throws RouterException {
        String[] rawTask = {"POST", "/rooms/LS1/bookings/create", "uid=ttavares@slb.pt"
                + "&begin=2020-04-08+08:30:00"
                + "&duration=45"};
        CommandRequest userRequest = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof PostBooking);
    }

}
