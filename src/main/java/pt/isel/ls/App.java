package pt.isel.ls;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Router router = new Router();
        router.initRoutes();
        UserInterface ui = new UserInterface();
        String[] rawTask;

        if (args.length > 0) {
            rawTask = args;
            executeTask(router, ui, rawTask);
        } else {
            while (true) {
                ui.askForCommand();
                rawTask = new Scanner(System.in).nextLine().split(" ");
                executeTask(router, ui, rawTask);
            }
        }
    }

    private static void executeTask(Router router, UserInterface ui, String[] rawTask) {
        CommandRequest userRequest;
        userRequest = new CommandRequest(Method.valueOf(rawTask[0]), new Path(rawTask[1]),
                UtilMethods.getParameters(rawTask));
        RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
        userRequest.setParameter(
                UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
        CommandResult commandResult = routeResult.getHandler().execute(userRequest);
        ui.show(commandResult);
    }
}


