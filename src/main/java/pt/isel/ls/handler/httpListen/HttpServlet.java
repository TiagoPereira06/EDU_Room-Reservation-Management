package pt.isel.ls.handler.httpListen;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.http.Servlet;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;
import java.util.List;

public class HttpServlet implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);

    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new Servlet()), "/*");
        server.start();
        String status = "Server started";
        log.info(status);
        return new ListenResult(List.of(List.of(status)));
    }

    @Override
    public String description() {
        return "Starts server, recieves port and waits for http command";
    }
}
