package pt.isel.ls;

import pt.isel.ls.handler.ResultInterface;
import pt.isel.ls.request.Header;

import java.io.IOException;

public interface OutputResult {

    void show(ResultInterface resultInterface, Header header) throws IOException;

    void showError(String s);
}
