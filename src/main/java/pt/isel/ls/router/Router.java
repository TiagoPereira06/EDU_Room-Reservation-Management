package pt.isel.ls.router;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.Exit;
import pt.isel.ls.handler.booking.GetBooking;
import pt.isel.ls.handler.booking.GetBookingById;
import pt.isel.ls.handler.booking.GetBookingByOwner;
import pt.isel.ls.handler.booking.PostBooking;
import pt.isel.ls.handler.label.GetLabel;
import pt.isel.ls.handler.room.GetRoomsByLabel;
import pt.isel.ls.handler.label.PostLabel;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.handler.room.GetRoomById;
import pt.isel.ls.handler.room.PostRoom;
import pt.isel.ls.handler.user.GetUser;
import pt.isel.ls.handler.user.GetUserById;
import pt.isel.ls.handler.user.PostUser;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.request.Template;
import pt.isel.ls.request.Path;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.utils.UtilMethods;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;


public class Router {
    private Map<Key, CommandHandler> routesMap;

    public Router() {
        routesMap = new HashMap<>();
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

    public void addRoute(Method method, PathTemplate template, CommandHandler handler) {
        routesMap.put(new Key(method, template), handler);
    }


    public RouteResult findRoute(Method method, Path path) {
        RouteResult result = null;
        Iterator<Key> itr = routesMap.keySet().iterator();
        while (itr.hasNext() && result == null) {
            result = checkPathMatch(itr.next(), method, path);
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


    public void initRoutes() {
        this.addRoute(Method.POST, new PathTemplate(Template.ROOMS), new PostRoom());
        this.addRoute(Method.GET, new PathTemplate(Template.ROOMS), new GetRoom());
        this.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID), new GetRoomById());
        this.addRoute(Method.POST, new PathTemplate(Template.ROOMS_RID_BOOKINGS), new PostBooking());
        this.addRoute(Method.GET, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID), new GetBookingById());
        this.addRoute(Method.POST, new PathTemplate(Template.USERS), new PostUser());
        this.addRoute(Method.GET, new PathTemplate(Template.USERS), new GetUser());
        this.addRoute(Method.GET, new PathTemplate(Template.USERS_UID), new GetUserById());
        this.addRoute(Method.GET, new PathTemplate(Template.USERS_UID_BOOKINGS), new GetBookingByOwner());
        this.addRoute(Method.GET, new PathTemplate(Template.BOOKINGS), new GetBooking());
        this.addRoute(Method.POST, new PathTemplate(Template.LABELS), new PostLabel());
        this.addRoute(Method.GET, new PathTemplate(Template.LABELS), new GetLabel());
        this.addRoute(Method.GET, new PathTemplate(Template.LABELS_LID_ROOMS), new GetRoomsByLabel());
        this.addRoute(Method.EXIT, new PathTemplate(Template.OPTION), new Exit());
    }
}