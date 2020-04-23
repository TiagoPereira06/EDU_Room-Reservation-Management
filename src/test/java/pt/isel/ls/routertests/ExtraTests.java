package pt.isel.ls.routertests;

import junit.framework.Assert;
import org.junit.Test;
import pt.isel.ls.App;

import pt.isel.ls.handler.booking.DeleteBooking;
import pt.isel.ls.handler.booking.PutBooking;
import pt.isel.ls.handler.option.Option;
import pt.isel.ls.handler.time.Time;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

public class ExtraTests {

    private Router router;

    public ExtraTests() {
        router = new Router();
        App.initRoutes(router);
    }

    @Test
    public void deleteBooking() throws NoSuchMethodException {
        String[] rawTask = {"DELETE", "/rooms/bernassilva@slb.pt/bookings/6"};
        CommandRequest request = App.formatUserInput(rawTask);
        RouteResult routeResult = router.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof DeleteBooking);
    }

    @Test
    public void putBooking() throws NoSuchMethodException {
        String[] rawTask = {"PUT", "/rooms/ttavares@slb.pt/bookings/4", "uid=ttavares@slb.pt"
                + "&begin=2020-04-24+09:30:00"
                + "&duration=90"};
        CommandRequest request = App.formatUserInput(rawTask);
        RouteResult routeResult = router.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof PutBooking);
    }

    @Test
    public void getTime() throws NoSuchMethodException {
        String[] rawTask = {"GET", "/time"};
        CommandRequest request = App.formatUserInput(rawTask);
        RouteResult routeResult = router.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof Time);
    }

    @Test
    public void testOption() throws NoSuchMethodException {
        String[] rawTask = {"OPTION", "/"};
        CommandRequest request = App.formatUserInput(rawTask);
        RouteResult routeResult = router.findRoute(request.getMethod(), request.getPath());
        request.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), request.getParameter()));
        Assert.assertTrue(routeResult.getHandler() instanceof Option);
    }
}
