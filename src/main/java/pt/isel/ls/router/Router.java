package pt.isel.ls.router;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.Path;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.utils.UtilMethods;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


public class Router {
    private Map<Key, CommandHandler> routesMap;

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

    private static class Key {
        private Method method;
        private PathTemplate pathTemplate;

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