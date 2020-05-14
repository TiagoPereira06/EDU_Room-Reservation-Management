package pt.isel.ls.handler.listener;

import pt.isel.ls.App;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.ResultInterface;

import pt.isel.ls.request.CommandRequest;



import java.sql.Connection;

public class Listen implements CommandHandler {


    @Override
    public ResultInterface execute(CommandRequest commandRequest, Connection connection)  {
        return new ListenResult(App.router.getRoutes());
    }

    @Override
    public String description() {
        return "Starts server, recieves port and waits for http command";
    }
}
