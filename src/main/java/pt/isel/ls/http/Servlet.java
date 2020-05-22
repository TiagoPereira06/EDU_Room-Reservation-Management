package pt.isel.ls.http;

import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.App;
import pt.isel.ls.ServerInterface;
import pt.isel.ls.router.Router;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Servlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Servlet.class);
    Router router = App.router;
    ServerInterface serverInterface;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        serverInterface = new ServerInterface(resp);
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<String> rawTask = new ArrayList<>();
            String method = req.getMethod();
            String reqUriRaw = ((Request) req).getUri().toString();
            List<String> reqParts = Arrays.asList(
                    reqUriRaw.replace("?", " ")
                            .replace("%20", " ")
                            .split(" "));
            log.info(String.format("Incoming Request: ME->%s||URI->%s", method, reqUriRaw));
            rawTask.add(method);
            rawTask.addAll(reqParts);
            delegateTask(rawTask.toArray(new String[0]));
        } catch (Exception e) {
            serverInterface.showError(e.getMessage());
        }
    }

    public void delegateTask(String[] rawTask) throws NoSuchMethodException {
        App.executeTask(router, serverInterface, rawTask);
    }

}
