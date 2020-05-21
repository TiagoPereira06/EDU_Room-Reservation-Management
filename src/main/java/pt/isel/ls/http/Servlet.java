package pt.isel.ls.http;

import pt.isel.ls.App;
import pt.isel.ls.ServerInterface;
import pt.isel.ls.router.Router;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Servlet extends HttpServlet {
    Router router = App.router;
    ServerInterface serverInterface = new ServerInterface();

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        process(req,resp);
    }

    private void process(HttpServletRequest req,HttpServletResponse resp) {
        try {
            serverInterface.setResp(resp);
            List<String> rawTask = new ArrayList<>();
            rawTask.add(req.getMethod());
            rawTask.add(req.getRequestURI());
            delegateTask(rawTask.toArray(new String[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delegateTask(String[] rawTask) throws NoSuchMethodException {
        App.executeTask(router,serverInterface,rawTask);
    }

}
