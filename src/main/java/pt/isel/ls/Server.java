package pt.isel.ls;

import pt.isel.ls.router.Router;

import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        LocalInterface local = new LocalInterface();
        Router serverRouter = App.initRouterBehaviour();
        String[] rawTask = new String[]{"LISTEN", "/"};
        try {
            App.executeTask(serverRouter, local, rawTask);
        } catch (NoSuchMethodException | SQLException e) {
            local.showError(e);
        }
    }


}
