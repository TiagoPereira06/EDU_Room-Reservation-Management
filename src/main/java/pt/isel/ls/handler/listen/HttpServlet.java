package pt.isel.ls.handler.listen;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.http.Servlet;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

public class HttpServlet implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);

    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) throws Exception {
        //TODO: ACCEPT DIFFERENT PORTS
        //String port = commandRequest.getParametersByName("port");
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new Servlet()), "/*");
        server.start();
        String status = "Server started";
        log.info(status);
        return new HttpServletView();
    }

    @Override
    public String description() {
        return "Starts server, receives port and waits for http command";
    }
}
