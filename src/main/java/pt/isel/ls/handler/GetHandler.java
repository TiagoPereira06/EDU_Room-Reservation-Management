package pt.isel.ls.handler;

import pt.isel.ls.request.Method;

public abstract class GetHandler implements CommandHandler {
    public Method getMethod() {
        return Method.GET;
    }
}
