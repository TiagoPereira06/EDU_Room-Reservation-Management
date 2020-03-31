package pt.isel.ls.utils;

import junit.framework.Assert;
import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.Path;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;

import java.sql.Connection;
import java.util.LinkedList;

public class RouterTest {
    private final String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";
    private Connection con;
    private PGSimpleDataSource ds;

    private static Router router = new Router();

    @Test
    public void routerTest() {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        Path path = new Path("/rooms");
        LinkedList<Parameter> param = new LinkedList<>();
        Parameter ex1 = new Parameter("name", "LS3");
        param.add(ex1);
        CommandRequest cr = new CommandRequest(Method.GET, path, param);
        router.initRoutes();
        RouteResult res = router.findRoute(Method.GET, path);

        Assert.assertTrue(res.getHandler() instanceof GetRoom);


    }
}
