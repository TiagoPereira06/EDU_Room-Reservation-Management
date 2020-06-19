package pt.isel.ls.handler.listen;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.ServerException;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.http.Servlet;
import pt.isel.ls.request.CommandRequest;

public class HttpServlet implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);

    @Override
    public CommandResult execute(CommandRequest commandRequest) throws CommandException {
        Server server;
        try {
            int port = Integer.parseInt(commandRequest.getParameterByName("port"));
            server = new Server(port);
        } catch (Exception e) {
            String portDef = System.getenv("PORT");
            if (portDef == null) {
                server = new Server(8080);
            } else {
                server = new Server(Integer.parseInt(portDef));
            }
        }
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(new ServletHolder(new Servlet()), "/*");
        try {
            server.start();
        } catch (Exception e) {
            throw new ServerException();
        }
        log.info("Server is now online");
        return new HttpServletResult();
    }

    @Override
    public String description() {
        return "Starts server, receives port and waits for http command";
    }
}
