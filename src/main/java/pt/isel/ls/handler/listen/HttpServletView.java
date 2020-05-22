package pt.isel.ls.handler.listen;

import pt.isel.ls.handler.ResultView;

public class HttpServletView implements ResultView {

    @Override
    public String htmlOutput() {
        return null;
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
