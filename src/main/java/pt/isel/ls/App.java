package pt.isel.ls;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Parameter;
import pt.isel.ls.request.Path;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;

import java.util.List;


public class App {
    public static void main(String[] args) {
        //MODO INTERATIVO -> LOOP ATÉ COMANDO EXIT (ARGS.LENGHT=0)
        //MODO COMANDO -> EXECUTA COMANDO E SAI

        //STRING USEr
        //Inicializar Comandos do Router
        //MANDAR -> ROUTER A STRING (RETORNA O COMANDO HANDLER)
        //COMANDO.EXECUTAR
        //CONSOLA.PRINT -> RESULTADO DO COMANDO

        /*
         */
        //Adicionar os comandos ao route

        Router router = new Router();
        router.initRoutes();

        //MODO CONSOLA
        if (args.length > 0) {
            List<Parameter> parameterList;
            CommandRequest userRequest = new CommandRequest(Method.valueOf(args[0]), new Path(args[1]),
                    UtilMethods.getParameters(args));
            RouteResult routeResult = router.findRoute(userRequest.getMethod(), userRequest.getPath());
            userRequest.setParameter(
                    UtilMethods.concatTwoLists(routeResult.getParameters(), userRequest.getParameter()));
            CommandResult commandResult = routeResult.getHandler().execute(userRequest);
            UserInterface ui = new UserInterface();
            ui.show(commandResult);
        }
    }


}
