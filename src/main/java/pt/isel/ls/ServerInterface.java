package pt.isel.ls;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.request.Header;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ServerInterface implements OutputResult {

    private HttpServletResponse resp;
    private ServletOutputStream outputStream;

    @Override
    public void show(ResultInterface resultInterface, Header header) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        formatHtml(resultInterface, stringBuilder);
        resp.setStatus(200);
        byte[] respBytes = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
        outputStream.write(respBytes);
        outputStream.flush();

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


    public void formatHtml(ResultInterface resultInterface, StringBuilder stringBuilder) {
        stringBuilder.append(headerFormat(resultInterface));
        stringBuilder.append(titleFormat(resultInterface));
        stringBuilder.append(tableResultFormat(resultInterface));
        stringBuilder.append(footerFormat());

    }

    private String titleFormat(ResultInterface resultInterface) {
        return "<h1>" + resultInterface.description() + "</h1>";
    }

    private String headerFormat(ResultInterface resultInterface) {
        return "<html><head><title>" + resultInterface.description() + "</title></head><body>";
    }

    private String tableResultFormat(ResultInterface resultInterface) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=1>");
        sb.append(getColumns(resultInterface.columns()));
        for (List<String> rows : resultInterface.values()) {
            sb.append(getRows(rows));
        }
        sb.append("</table>");
        return sb.toString();
    }


    private String getRows(List<String> rows) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
        for (String row : rows) {
            sb.append("<td>");
            sb.append(row);
            sb.append("</td>");
        }
        sb.append("</tr>");
        return sb.toString();

    }

    private String getColumns(List<String> columns) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>");
        for (String column : columns) {
            sb.append("<th>");
            sb.append(column);
            sb.append("</th>");
        }
        sb.append("</tr>");
        return sb.toString();
    }

    private String footerFormat() {
        return "</body></html>";
    }

    public void setResp(HttpServletResponse resp) throws IOException {
        this.resp = resp;
        outputStream = resp.getOutputStream();
    }
}
