package pt.isel.ls;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.output.HtmlOutput;
import pt.isel.ls.output.OutputInterface;
import pt.isel.ls.output.PlainTextOutput;
import pt.isel.ls.output.TextType;
import pt.isel.ls.request.Header;
import pt.isel.ls.request.HeaderType;
import pt.isel.ls.request.HeaderValue;

import java.io.IOException;

public class UserInterface {
    public void show(ResultInterface resultInterface, Header header) throws IOException {
        if (resultInterface == null) {
            showError("Empty Result");
            return;
        }
        showSuccess();
        TextType outputType = TextType.valueOf(
                header.getValue(HeaderType.ACCEPT).getValue()
                        .split("/")[1].toUpperCase());

        HeaderValue hv = header.getValue(HeaderType.FILENAME);
        String filename = "";
        if (hv != null) {
            filename = hv.getValue();
        }

        OutputInterface outputInterface = (outputType == TextType.PLAIN)
                ? new PlainTextOutput(resultInterface) : new HtmlOutput(resultInterface);

        if ((filename.isEmpty())) {
            outputInterface.printToConsole();
        } else {
            outputInterface.printToFile(filename);
        }

    }

    private void showSuccess() {
        System.out.print("\n--- SUCCESS ---\n");
    }

    public void askForCommand() {
        System.out.println("\nEnter a command:");
        System.out.println("{Method} {Path} {Arguments}");
        System.out.print("-> ");
    }

    public void showError(String s) {
        System.out.println("ERROR : " + s.toUpperCase() + " !");
    }
}
