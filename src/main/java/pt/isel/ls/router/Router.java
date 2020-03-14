package pt.isel.ls.router;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;

import java.util.HashMap;
import java.util.Map;


public class Router {
    private Map<Key, CommandHandler> routesMap;

    public Router() {
        routesMap = new HashMap<>();
    }

    private static class Key {

        private Key(Method method, String template) {
        }
    }

    public void addRoute(Method method, String template, CommandHandler handler) {
        routesMap.put(new Key(method, template), handler);
    }


    public RouteResult findRoute(Method method, Path path) {
        return null;
    }

}

