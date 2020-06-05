package pt.isel.ls.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.App;
import pt.isel.ls.ServerInterface;
import pt.isel.ls.router.CommandRouter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Servlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Servlet.class);
    CommandRouter commandRouter = App.initCommandRouterBehaviour();
    ServerInterface serverInterface;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        serverInterface = new ServerInterface(resp);
        process(req);
    }

    private void process(HttpServletRequest req) {
        try {
            List<String> rawTask = new ArrayList<>();
            String method = req.getMethod();
            String requestUri = req.getRequestURI();
            log.info(String.format("Incoming Request: ME->%s||URI->%s", method, requestUri));
            String parameters = req.getQueryString();
            List<String> reqParts = formatUrl(parameters);
            rawTask.add(method);
            rawTask.add(requestUri);
            rawTask.addAll(reqParts);
            delegateTask(rawTask.toArray(new String[0]));
        } catch (Exception e) {
            serverInterface.showError(e);
        }
    }

    private List<String> formatUrl(String reqUriRaw) {
        if (reqUriRaw == null) {
            return Collections.emptyList();
        }
        String replace = reqUriRaw
                .replace("?", " ")
                .replace("%20", " ");
        if (reqUriRaw.contains("%3A")) {
            replace = replace.replace("%3A", ":")
                    .replace("T", "+");
        }

        return Arrays.asList(replace.split(" "));

    }

    public void delegateTask(String[] rawTask) throws Exception {
        App.executeTask(commandRouter, serverInterface, rawTask);
    }

}
