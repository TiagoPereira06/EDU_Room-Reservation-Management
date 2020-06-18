package pt.isel.ls.userinterfaces.interfaces;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.Header;

public interface OutputInterface {

    void show(CommandResult commandResult, Header header) throws Exception;

    void showError(Exception e);
}
