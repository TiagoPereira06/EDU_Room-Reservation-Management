package pt.isel.ls;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.Exit;
import pt.isel.ls.handler.booking.GetBookingById;
import pt.isel.ls.handler.booking.GetBookingByOwner;
import pt.isel.ls.handler.booking.PostBooking;
import pt.isel.ls.handler.label.GetLabel;
import pt.isel.ls.handler.label.GetRoomsByLabel;
import pt.isel.ls.handler.label.PostLabel;
import pt.isel.ls.handler.room.GetRoom;
import pt.isel.ls.handler.room.GetRoomById;
import pt.isel.ls.handler.room.PostRoom;
import pt.isel.ls.handler.user.GetUserById;
import pt.isel.ls.handler.user.PostUser;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Path;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.router.RouteResult;
import pt.isel.ls.router.Router;
import pt.isel.ls.utils.UtilMethods;


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

        router.addRoute(Method.POST, PathTemplate.ROOMS, new PostRoom());
        router.addRoute(Method.GET, PathTemplate.ROOMS, new GetRoom());
        router.addRoute(Method.GET, PathTemplate.ROOMS_RID, new GetRoomById());

        router.addRoute(Method.POST, PathTemplate.ROOMS_RID_BOOKINGS, new PostBooking());
        router.addRoute(Method.GET, PathTemplate.ROOMS_RID_BOOKINGS_BID, new GetBookingById());

        router.addRoute(Method.POST, PathTemplate.USERS, new PostUser());
        router.addRoute(Method.GET, PathTemplate.USERS_UID, new GetUserById());
        router.addRoute(Method.GET, PathTemplate.USERS_UID_BOOKINGS, new GetBookingByOwner());

        router.addRoute(Method.POST, PathTemplate.LABELS, new PostLabel());
        router.addRoute(Method.GET, PathTemplate.LABELS, new GetLabel());
        router.addRoute(Method.GET, PathTemplate.LABELS_LID_ROOMS, new GetRoomsByLabel());

        router.addRoute(Method.EXIT, PathTemplate.OPTION, new Exit());

        //MODO CONSOLA

        if (args.length > 0) {
            Method method = UtilMethods.checkMethod(args[0]);
            RouteResult command = router.findRoute(method, new Path(args[1]));
            CommandResult commandResult = command.getHandler().execute(new CommandRequest(null, null, null));
            UserInterface ui = new UserInterface();
            ui.show(commandResult);
        }
    }
}
