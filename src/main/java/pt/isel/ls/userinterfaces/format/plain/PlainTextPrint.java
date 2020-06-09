package pt.isel.ls.userinterfaces.format.plain;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.userinterfaces.format.PrintInterface;

import java.io.IOException;
import java.io.OutputStream;

import static pt.isel.ls.utils.UtilMethods.getBytes;

public class PlainTextPrint implements PrintInterface {
    private final ResultView resultView;

    public PlainTextPrint(ResultView resultView) {
        this.resultView = resultView;
    }

    @Override
    public void printTo(OutputStream outputStream) throws IOException {
        outputStream.write(getBytes(resultView.plainOutput()));
        outputStream.flush();
    }
}

