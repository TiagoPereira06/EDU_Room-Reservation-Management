package pt.isel.ls.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.Exit;
import pt.isel.ls.handler.booking.GetBookingById;
import pt.isel.ls.handler.label.PostLabel;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.handler.user.GetUserById;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;

public class RouterTest {
    private final String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";
    private PGSimpleDataSource ds;

    private static Router router = new Router();

    @Test
    public void routerTest1() {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        Path path = new Path("/rooms");
        router.initRoutes();
        RouteResult res = router.findRoute(Method.GET, path);

        Assert.assertTrue(res.getHandler() instanceof GetRoom);
    }

    @Test
    public void routerTest2() {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        Path path = new Path("/users/marciogarcia@gmail.com");

        router.initRoutes();
        RouteResult res = router.findRoute(Method.GET, path);

        Assert.assertTrue(res.getHandler() instanceof GetUserById);
    }

    @Test
    public void routerTest3() {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        Path path = new Path("/rooms/ttavares@slb.pt/bookings/1");

        router.initRoutes();
        RouteResult res = router.findRoute(Method.GET, path);

        Assert.assertTrue(res.getHandler() instanceof GetBookingById);
    }

    @Test
    public void routerTest4() {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        Path path = new Path("/labels");

        router.initRoutes();
        RouteResult res = router.findRoute(Method.POST, path);

        Assert.assertTrue(res.getHandler() instanceof PostLabel);
    }

    @Test
    public void routerTest5() {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        Path path = new Path("/");

        router.initRoutes();
        RouteResult res = router.findRoute(Method.EXIT, path);
        Assert.assertTrue(res.getHandler() instanceof Exit);
    }
}
