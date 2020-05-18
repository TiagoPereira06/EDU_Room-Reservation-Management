package pt.isel.ls.output;

import pt.isel.ls.handler.ResultInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HtmlOutput implements OutputInterface {
    private final ResultInterface resultInterface;
    private final StringBuilder stringBuilder;

    public HtmlOutput(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
        stringBuilder = new StringBuilder();
        formatHtml();
    }

    public void formatHtml() {
        stringBuilder.append(headerFormat());
        stringBuilder.append(titleFormat());
        stringBuilder.append(tableResultFormat());
        stringBuilder.append(footerFormat());

    }

    private String titleFormat() {
        return "<h1>" + resultInterface.description() + "</h1>";
    }

    private String headerFormat() {
        return "<html><head><title>" + resultInterface.description() + "</title></head><body>";
    }

    private String tableResultFormat() {
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


    @Override
    public void printToConsole() {
        System.out.println(getResult());

    }

    @Override
    public void printToFile(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8);
        writer.println(stringBuilder.toString());
        writer.close();
    }

    public String getResult(){
        return stringBuilder.toString();
    }

}
