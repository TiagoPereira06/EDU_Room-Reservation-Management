package pt.isel.ls;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.print.HtmlPrint;
import pt.isel.ls.print.PlainTextPrint;
import pt.isel.ls.print.PrintInterface;
import pt.isel.ls.print.TextType;
import pt.isel.ls.request.Header;

import java.io.FileOutputStream;
import java.io.IOException;

import static pt.isel.ls.request.Header.getFilename;
import static pt.isel.ls.request.Header.getOutputType;

public class LocalInterface implements OutputInterface {
    @Override
    public void show(ResultView resultView, Header header) throws IOException {
        if (resultView == null /*|| resultView.values().isEmpty()*/) {
            showError("Empty Result");
            return;
        }
        showSuccess();
        TextType outputType = getOutputType(header);
        String filename = getFilename(header);
        PrintInterface printInterface;
        if (outputType == TextType.PLAIN) {
            printInterface = new PlainTextPrint(resultView);
        } else {
            printInterface = new HtmlPrint(resultView);
        }
        if ((filename.isEmpty())) {
            printInterface.printTo(System.out);
        } else {
            printInterface.printTo(new FileOutputStream(filename));
        }
    }

    private void showSuccess() {
        System.out.print("\n--- SUCCESS ---\n");
    }

    public void askForCommand() {
        System.out.println("\nEnter a command:");
        System.out.println("{Method} {Path} {Headers} {Arguments}");
        System.out.print("-> ");
    }

    @Override
    public void showError(String s) {
        System.out.println("\nERROR : " + s.toUpperCase() + " !");
    }

}
