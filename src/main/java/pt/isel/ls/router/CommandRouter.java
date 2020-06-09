package pt.isel.ls.router;

import pt.isel.ls.CommandUri;
import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.Path;
import pt.isel.ls.utils.UtilMethods;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class CommandRouter {
    private final List<Operation> routes;

    public CommandRouter() {
        routes = new LinkedList<>();
    }

    public void addRoute(CommandUri commandUri, CommandHandler handler) {
        routes.add(new Operation(commandUri, handler));
    }

    public RouteResult findRoute(Method method, Path path) throws NoSuchMethodException {
        RouteResult result = null;
        Iterator<Operation> itr = routes.iterator();
        while (itr.hasNext() && result == null) {
            result = checkPathMatch(itr.next(), method, path);
        }
        if (result == null) {
            throw new NoSuchMethodException("Request Not Found");
        }
        return result;
    }

    private RouteResult checkPathMatch(Operation insertedOperation, Method method, Path path) {
        String[] insertedPath = path.getPath().split("/");
        insertedPath = UtilMethods.filterStringArray(insertedPath);

        String[] referencePath = insertedOperation.commandUri.pathTemplate.getPath().split("/");
        referencePath = UtilMethods.filterStringArray(referencePath);

        if (insertedOperation.commandUri.method == method && referencePath.length == insertedPath.length) {
            List<Parameter> parameterList = new LinkedList<>();
            int i = 0;
            for (; i < insertedPath.length; ++i) {
                if (referencePath[i].contains("{") && referencePath[i].contains("}")) {
                    parameterList.add(new Parameter((referencePath[i]), insertedPath[i]));
                    continue;
                }
                if (!(insertedPath[i].equals(referencePath[i]))) {
                    return null;
                }
            }
            if (i == insertedPath.length) {

                return new RouteResult(insertedOperation.commandHandler, parameterList);
            }
        }
        return null;
    }

    public List<List<String>> getRoutes() {
        LinkedList<List<String>> list = new LinkedList<>();
        for (Operation operation : routes) {
            List<String> row = new LinkedList<>();
            row.add(operation.commandUri.method.toString() + " " + operation.commandUri.pathTemplate.getPath());
            row.add(operation.commandHandler.description());
            list.add(row);
        }
        return list;
    }

    private class Operation {
        public CommandUri commandUri;
        public CommandHandler commandHandler;

        public Operation(CommandUri commandUri, CommandHandler commandHandler) {
            this.commandUri = commandUri;
            this.commandHandler = commandHandler;
        }
    }
}