package pt.isel.ls;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.print.HtmlPrint;
import pt.isel.ls.print.PrintInterface;
import pt.isel.ls.request.Header;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServerInterface implements OutputInterface {

    private final HttpServletResponse resp;
    private ServletOutputStream outputStream;

    public ServerInterface(HttpServletResponse resp) {
        this.resp = resp;
        try {
            outputStream = resp.getOutputStream();
        } catch (IOException e) {
            showError(e.getMessage());
        }
    }

    @Override
    public void show(ResultView resultView, Header header) throws IOException {
        PrintInterface htmlPrint = new HtmlPrint(resultView);
        resp.setStatus(200);
        htmlPrint.printTo(outputStream);
    }

    @Override
    public void showError(String s) {
        try {
            resp.setStatus(404);
            outputStream.print("ERROR : " + s.toUpperCase() + " !");
            outputStream.flush();
        } catch (IOException e) {
            showError(e.getMessage());
        }

    }
}
