package pt.isel.ls.router;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.Parameter;

import java.util.List;

public class RouteResult {
    private CommandHandler handler;
    private List<Parameter> parameters;

    public RouteResult(CommandHandler handler, List<Parameter> parameters) {
        this.handler = handler;
        this.parameters = parameters;
    }

    public RouteResult() {

    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public void setHandler(CommandHandler handler) {
        this.handler = handler;
    }
}
