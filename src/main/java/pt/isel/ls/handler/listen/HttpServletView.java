package pt.isel.ls.handler.listen;

import pt.isel.ls.handler.View;

public class HttpServletView extends View {

    @Override
    public String htmlOutput() {
        return "Only Available on PLAIN TEXT Support";
    }

    @Override
    public String plainOutput() {
        return "Server Started";
    }


    @Override
    public String name() {
        return "App is now HTTP compatible";
    }

}
