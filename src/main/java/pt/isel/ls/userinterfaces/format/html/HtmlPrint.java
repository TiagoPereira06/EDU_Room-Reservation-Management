package pt.isel.ls.userinterfaces.format.html;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.userinterfaces.format.PrintInterface;

import java.io.IOException;
import java.io.OutputStream;

import static pt.isel.ls.utils.UtilMethods.getBytes;

public class HtmlPrint implements PrintInterface {
    private final CommandResult commandResult;

    public HtmlPrint(CommandResult commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public void printTo(OutputStream outputStream) throws IOException {
        outputStream.write(getBytes(commandResult.htmlOutput()));
        outputStream.flush();
        outputStream.close();
    }

}
