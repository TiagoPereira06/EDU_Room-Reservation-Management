package pt.isel.ls;

import pt.isel.ls.router.CommandRouter;
import pt.isel.ls.userinterfaces.interfaces.LocalInterface;

public class Server {
    public static void main(String[] args) {
        LocalInterface local = new LocalInterface();
        CommandRouter serverCommandRouter = App.initRouterBehaviour();
        String[] rawTask = new String[]{"LISTEN", "/"};
        try {
            App.executeTask(serverCommandRouter, local, rawTask);
        } catch (Exception e) {
            local.showError(e);
        }
    }


}
