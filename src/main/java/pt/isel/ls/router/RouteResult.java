package pt.isel.ls.router;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.Parameter;

import java.util.List;

public class RouteResult {
    private CommandHandler commandHandler;
    private List<Parameter> parameters;

    public RouteResult(CommandHandler commandHandler, List<Parameter> parameters) {
        this.commandHandler = commandHandler;
        this.parameters = parameters;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public void setCommandHandler(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
}
