package pt.isel.ls.handler;

import pt.isel.ls.request.Method;

public abstract class PostHandler implements CommandHandler {
    public Method getMethod() {
        return Method.POST;
    }
}
