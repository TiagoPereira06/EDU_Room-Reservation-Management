package pt.isel.ls;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.handler.result.html.ErrorTemplate;
import pt.isel.ls.http.StatusCode;
import pt.isel.ls.print.HtmlPrint;
import pt.isel.ls.print.PrintInterface;
import pt.isel.ls.request.Header;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

public class ServerInterface implements OutputInterface {

    private final HttpServletResponse resp;
    private ServletOutputStream outputStream;

    public ServerInterface(HttpServletResponse resp) {
        this.resp = resp;
        try {
            outputStream = resp.getOutputStream();
        } catch (IOException e) {
            showError(e);
        }
    }

    @Override
    public void show(ResultView resultView, Header header) throws IOException {
        PrintInterface htmlPrint = new HtmlPrint(resultView);
        resp.setStatus(StatusCode.Ok.getCodeValue());
        htmlPrint.printTo(outputStream);
    }

    @Override
    public void showError(Exception e) {
        int status = AppError.getStatusCode(e);
        try {
            resp.setStatus(status);
            outputStream.write(ErrorTemplate.errorTemplate(e.getMessage()).getBytes(Charset.defaultCharset()));
            outputStream.flush();
        } catch (IOException es) {
            showError(es);
        }

    }
}
