package pt.isel.ls.http;

import pt.isel.ls.request.*;
import pt.isel.ls.router.Router;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Servlet extends HttpServlet {
    private Router router;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Path path = new Path(req.getRequestURI());
        Method method = Method.valueOf(req.getMethod());
        Header header = new Header(req.getHeader("Accept"));
       // forma2 Header header2 = new Header().setHeader(HeaderType.ACCEPT);
        CommandRequest request = new CommandRequest(method,path,null,header);
        executeReq(resp,request);
    }

    private void executeReq(HttpServletResponse resp, CommandRequest request) {

    }
}
