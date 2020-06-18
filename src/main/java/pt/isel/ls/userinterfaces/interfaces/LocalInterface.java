package pt.isel.ls.userinterfaces.interfaces;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.Header;
import pt.isel.ls.userinterfaces.format.PrintInterface;
import pt.isel.ls.userinterfaces.format.TextType;
import pt.isel.ls.userinterfaces.format.html.HtmlPrint;
import pt.isel.ls.userinterfaces.format.plain.PlainTextPrint;

import java.io.FileOutputStream;
import java.io.InvalidObjectException;

import static pt.isel.ls.request.Header.getFilename;
import static pt.isel.ls.request.Header.getOutputType;

public class LocalInterface implements OutputInterface {
    @Override
    public void show(CommandResult commandResult, Header header) throws Exception {
        if (commandResult == null) {
            showError(new InvalidObjectException("Empty Result"));
            return;
        }
        showSuccess();
        TextType outputType = getOutputType(header);
        String filename = getFilename(header);
        PrintInterface printInterface;
        if (outputType == TextType.PLAIN) {
            printInterface = new PlainTextPrint(commandResult);
        } else {
            printInterface = new HtmlPrint(commandResult);
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
    public void showError(Exception e) {
        System.out.println("\nERROR : " + e.getMessage() + " !");

    }

}
