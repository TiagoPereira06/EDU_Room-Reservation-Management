package pt.isel.ls;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.Header;

import java.io.IOException;

public interface OutputInterface {

    void show(ResultView resultView, Header header) throws IOException;

    void showError(String s);
}
