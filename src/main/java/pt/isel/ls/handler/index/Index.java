package pt.isel.ls.handler.index;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultView;
import pt.isel.ls.request.CommandRequest;

import java.sql.SQLException;
import java.text.ParseException;

public class Index implements CommandHandler {
    @Override
    public ResultView execute(CommandRequest commandRequest) throws SQLException, ParseException {
        return new IndexView();
    }

    @Override
    public String description() {
        return "Index of Server Application";
    }
}
