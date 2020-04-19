package pt.isel.ls.output;

import pt.isel.ls.handler.ResultInterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class PlainTextOutput implements OutputInterface {
    private final ResultInterface resultInterface;
    private final List<String> output;

    public PlainTextOutput(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
        output = new LinkedList<>();
        printFormat();
    }

    @Override
    public void printToConsole() {
        System.out.println(resultInterface.description());
        for (String row : output) {
            System.out.println(row);
        }
    }

    @Override
    public void printToFile(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8);
        writer.println(resultInterface.description());
        for (String row : output) {
            writer.println(row);
        }
        writer.close();
    }

    public void printFormat() {
        for (List<String> list : resultInterface.values()) { //NUMERO DE ROWS
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.size(); j++) { //ROW
                sb.append(resultInterface.columns().get(j).toUpperCase());
                sb.append(" : ");
                sb.append(list.get(j));
                sb.append("    ");
            }
            output.add(sb.toString());
        }
    }
}

