package pt.isel.ls.handler.listener;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.isel.ls.handler.Result;
import pt.isel.ls.http.Servlet;

import java.util.List;

public class ListenResult extends Result {
    private static final Logger log = LoggerFactory.getLogger(Listen.class);


    public ListenResult(List<List<String>> value) {
        super(value);
        try {
            Server server = new Server(80);
            ServletHandler handler = new ServletHandler();
            server.setHandler(handler);
            handler.addServletWithMapping(new ServletHolder(new Servlet()), "/*");
            server.start();
            log.info("Server started");
        }
        catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public List<String> columns() {
        return null;
    }

    @Override
    public String description() {
        return "Listens all";
    }
}
