package pt.isel.ls.userinterfaces.format.plain;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.userinterfaces.format.PrintInterface;

import java.io.IOException;
import java.io.OutputStream;

import static pt.isel.ls.utils.UtilMethods.getBytes;

public class PlainTextPrint implements PrintInterface {
    private final CommandResult commandResult;

    public PlainTextPrint(CommandResult commandResult) {
        this.commandResult = commandResult;
    }

    @Override
    public void printTo(OutputStream outputStream) throws IOException {
        outputStream.write(getBytes(commandResult.plainOutput()));
        outputStream.flush();
    }
}

