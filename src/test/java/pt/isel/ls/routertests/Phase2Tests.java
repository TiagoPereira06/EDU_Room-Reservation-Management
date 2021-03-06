package pt.isel.ls.routertests;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.ls.App;
import pt.isel.ls.errors.router.RouterException;
import pt.isel.ls.userinterfaces.interfaces.LocalInterface;
import pt.isel.ls.handler.booking.delete.DeleteBooking;
import pt.isel.ls.handler.booking.put.PutBooking;
import pt.isel.ls.handler.option.Option;
import pt.isel.ls.handler.time.Time;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.utils.UtilMethods;


public class Phase2Tests {

    private final CommandRouter commandRouter;

    public Phase2Tests() {
        commandRouter = new CommandRouter();
        App.initCommandRoutes(commandRouter);
    }

    @Test
    public void deleteBooking() throws RouterException {
        String[] rawTask = {"DELETE", "/rooms/bernassilva@slb.pt/bookings/6"};
        CommandRequest request = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof DeleteBooking);
    }

    @Test
    public void putBooking() throws RouterException {
        String[] rawTask = {"PUT", "/rooms/ttavares@slb.pt/bookings/4", "uid=ttavares@slb.pt"
                + "&begin=2020-04-24+09:30:00"
                + "&duration=90"};
        CommandRequest request = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof PutBooking);
    }

    @Test
    public void getTime() throws RouterException {
        String[] rawTask = {"GET", "/time"};
        CommandRequest request = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof Time);
    }

    @Test
    public void testOption() throws RouterException {
        String[] rawTask = {"OPTIONS", "/"};
        CommandRequest request = CommandRequest.formatUserInput(rawTask, new LocalInterface());
        RouteResult routeResult = commandRouter.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getCommandHandler() instanceof Option);
    }
}
