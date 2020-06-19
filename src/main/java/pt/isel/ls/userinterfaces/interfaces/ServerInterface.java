package pt.isel.ls.userinterfaces.interfaces;

import pt.isel.ls.errors.AppError;
import pt.isel.ls.errors.AppException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.ResponseHeader;
import pt.isel.ls.handler.Result;
import pt.isel.ls.request.Header;
import pt.isel.ls.userinterfaces.format.PrintInterface;
import pt.isel.ls.userinterfaces.format.html.HtmlPrint;
import pt.isel.ls.userinterfaces.format.html.htmlemitter.ErrorTemplate;

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
    public void show(CommandResult commandResult, Header header) throws Exception {
        PrintInterface htmlPrint = new HtmlPrint(commandResult);
        resp.setStatus(commandResult.successStatusCode().getCodeValue());
        ResponseHeader responseHeader = commandResult.responseHeader();
        if (responseHeader != null) {
            resp.setHeader(responseHeader.getName(), responseHeader.getValue());
        }
        htmlPrint.printTo(outputStream);
    }

    @Override
    public void showError(Exception e) {
        int status = AppError.getStatusCode(e);
        try {
            resp.setStatus(status);
            Result result = null;
            if (e instanceof AppException) {
                result = (((AppException) e).result);
            }
            if (result == null) {
                outputStream.write(
                        ErrorTemplate.errorTemplate(e.getMessage(), status)
                                .getBytes(Charset.defaultCharset())
                );
            } else {
                outputStream.write(result.htmlOutput().getBytes(Charset.defaultCharset()));
            }
            outputStream.flush();
        } catch (IOException es) {
            showError(es);
        }

    }
}
