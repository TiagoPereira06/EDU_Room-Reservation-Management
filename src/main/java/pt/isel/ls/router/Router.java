package pt.isel.ls.router;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.Path;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.utils.UtilMethods;

import java.util.*;


public class Router {
    private final Map<Key, CommandHandler> routesMap;

    public Router() {
        routesMap = new HashMap<>();
    }

    public void addRoute(Method method, PathTemplate template, CommandHandler handler) {
        routesMap.put(new Key(method, template), handler);
    }

    public RouteResult findRoute(Method method, Path path) throws NoSuchMethodException {
        RouteResult result = null;
        Iterator<Key> itr = routesMap.keySet().iterator();
        while (itr.hasNext() && result == null) {
            result = checkPathMatch(itr.next(), method, path);
        }
        if (result == null) {
            throw new NoSuchMethodException("Request Not Found");
        }
        return result;
    }

    private RouteResult checkPathMatch(Key routesMapKey, Method method, Path path) {
        String[] userPath = path.getPath().split("/");
        userPath = UtilMethods.filterStringArray(userPath);

        String[] foundPath = routesMapKey.getPathTemplate().getPath().split("/");
        foundPath = UtilMethods.filterStringArray(foundPath);

        if (routesMapKey.getMethod() == method && foundPath.length == userPath.length) {
            //PALAVRA/ARG/PALAVRA/ARG...-> Percorrer até fazer match
            List<Parameter> parameterList = new LinkedList<>();
            int i = 0;
            for (; i < userPath.length; i++) {
                if (i % 2 != 0) {
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
        for (Key key : routesMap.keySet()) {
            List<String> row = new LinkedList<>();
            row.add(key.method.toString() + " " + key.pathTemplate.getPath());
            row.add(routesMap.get(key).description());
            list.add(row);
        }
        return list;
    }

    private static class Key {
        private final Method method;
        private final PathTemplate pathTemplate;

        private Key(Method method, PathTemplate pathTemplate) {
            this.method = method;
            this.pathTemplate = pathTemplate;
        }

        public PathTemplate getPathTemplate() {
            return pathTemplate;
        }

        public Method getMethod() {
            return method;
        }
    }

}