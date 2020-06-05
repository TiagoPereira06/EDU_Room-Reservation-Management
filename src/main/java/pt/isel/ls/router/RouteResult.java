package pt.isel.ls.router;

import pt.isel.ls.CommandPackage;
import pt.isel.ls.request.Parameter;

import java.util.List;

public class RouteResult {
    private CommandPackage commandPackage;
    private List<Parameter> parameters;

    public RouteResult(CommandPackage commandPackage, List<Parameter> parameters) {
        this.commandPackage = commandPackage;
        this.parameters = parameters;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public CommandPackage getCommandPackage() {
        return commandPackage;
    }

    public void setCommandPackage(CommandPackage commandPackage) {
        this.commandPackage = commandPackage;
    }
}
