package pt.isel.ls.userinterfaces.interfaces;

import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.Header;

public interface OutputInterface {

    void show(ResultView resultView, Header header) throws Exception;

    void showError(Exception e);
}
