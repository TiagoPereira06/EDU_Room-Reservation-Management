package pt.isel.ls.router;

import pt.isel.ls.CommandPackage;
import pt.isel.ls.CommandUri;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.Path;
import pt.isel.ls.utils.UtilMethods;

import java.util.*;


public class CommandRouter {
    private final Map<CommandUri, CommandPackage> routesMap;

    public CommandRouter() {
        routesMap = new HashMap<>();
    }

    public void addRoute(CommandUri commandUri, CommandPackage commandPackage) {
        routesMap.put(commandUri, commandPackage);
    }

    public RouteResult findRoute(Method method, Path path) throws NoSuchMethodException {
        RouteResult result = null;
        Iterator<CommandUri> itr = routesMap.keySet().iterator();
        while (itr.hasNext() && result == null) {
            result = checkPathMatch(itr.next(), method, path);
        }
        if (result == null) {
            throw new NoSuchMethodException("Request Not Found");
        }
        return result;
    }

    private RouteResult checkPathMatch(CommandUri routesMapKey, Method method, Path path) {
        String[] userPath = path.getPath().split("/");
        userPath = UtilMethods.filterStringArray(userPath);

        String[] foundPath = routesMapKey.pathTemplate.getPath().split("/");
        foundPath = UtilMethods.filterStringArray(foundPath);

        if (routesMapKey.method == method && foundPath.length == userPath.length) {
            //PALAVRA/ARG/PALAVRA/ARG...-> Percorrer até fazer match
            List<Parameter> parameterList = new LinkedList<>();
            int i = 0;
            for (; i < userPath.length; i++) {
                if (i % 2 != 0 && foundPath[i].contains("{") && foundPath[i].contains("}")) {
                    parameterList.add(new Parameter((foundPath[i]), userPath[i]));
                    continue;
                }
                if (!(userPath[i].equals(foundPath[i]))) {
                    break;
                }
            }
            //Chegou ao fim da verificação porque fez match
            if (i == userPath.length) {
                return new RouteResult(routesMap.get(routesMapKey), parameterList);
            }
        }
        return null;
    }

    public List<List<String>> getRoutes() {
        LinkedList<List<String>> list = new LinkedList<>();
        for (CommandUri key : routesMap.keySet()) {
            List<String> row = new LinkedList<>();
            row.add(key.method.toString() + " " + key.pathTemplate.getPath());
            row.add(routesMap.get(key).handler.description());
            list.add(row);
        }
        return list;
    }

}