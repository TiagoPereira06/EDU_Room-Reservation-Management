package pt.isel.ls.handler.index;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.CommandRequest;

import java.sql.Connection;

public class Index implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest, Connection connection) {
        return new IndexView();
    }

    @Override
    public String description() {
        return "Index of Server Application";
    }
}
