package pt.isel.ls.handler.listen;

import pt.isel.ls.handler.Result;

public class HttpServletResult extends Result {

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
