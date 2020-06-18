package pt.isel.ls.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.App;
import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.userinterfaces.interfaces.ServerInterface;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class Servlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Servlet.class);
    CommandRouter commandRouter = App.initRouterBehaviour();
    ServerInterface serverInterface;

    private static List<String> formatUrl(String reqUriRaw) {
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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        serverInterface = new ServerInterface(resp);
        processGet(req);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        serverInterface = new ServerInterface(resp);
        processPost(req);
    }

    private void processGet(HttpServletRequest req) {
        List<String> rawTask = new ArrayList<>();
        String method = req.getMethod();
        String requestUri = formatUri(req.getRequestURI());
        log.info(String.format("Incoming Request: ME->%s||URI->%s", method, requestUri));
        String parameters = req.getQueryString();
        List<String> reqParts = formatUrl(parameters);
        rawTask.add(method);
        rawTask.add(requestUri);
        rawTask.addAll(reqParts);
        try {
            delegateTask(rawTask.toArray(new String[0]));
        } catch (Exception e) {
            serverInterface.showError(e);
        }
    }

    private String formatUri(String requestUri) {
        return requestUri.replace("%20", "+");
    }

    private void processPost(HttpServletRequest req) {
        String method = req.getMethod();
        String requestUri = req.getRequestURI();
        String parameters = getBodyParameters(req.getParameterMap());
        log.info(String.format("Incoming Request: ME->%s||URI->%s||PARAM->%s", method, requestUri, parameters));
        String[] rawTask = {method, requestUri, parameters};
        try {
            delegateTask(rawTask);
        } catch (Exception e) {
            serverInterface.showError(e);
        }
    }

    public void delegateTask(String[] rawTask) throws Exception {
        App.executeTask(commandRouter, serverInterface, rawTask);
    }

    private String getBodyParameters(Map<String, String[]> parameterMap) {
        StringBuilder sb = new StringBuilder();
        final String[] keys = parameterMap.keySet().toArray(new String[0]);
        for (int i = 0; i < keys.length; i++) {
            String key = keys[i];
            final String[] values = parameterMap.get(key);
            for (int j = 0; j < values.length; j++) {
                sb.append(key).append("=").append(values[j].replace(" ", "+"));
                if (j != values.length - 1) {
                    sb.append("&");
                }
            }
            if (i != keys.length - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }
}
