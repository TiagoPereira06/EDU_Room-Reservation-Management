package pt.isel.ls.handler;

import pt.isel.ls.request.Method;
import pt.isel.ls.request.Request;
import pt.isel.ls.result.Result;
import pt.isel.ls.template.Template;

public class Exit implements CommandHandler {
    @Override
    public Result execute(Request commandRequest) {
        return null;
    }

    @Override
    public Template getTemplate() {
        return null;
    }

    @Override
    public Method getMethod() {
        return Method.EXIT;
    }

    @Override
    public String description() {
        return "Exit";
    }
}
