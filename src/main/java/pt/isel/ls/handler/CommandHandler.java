package pt.isel.ls.handler;

import pt.isel.ls.request.Request;
import pt.isel.ls.result.Result;
import pt.isel.ls.request.Method;
import pt.isel.ls.template.Template;


public interface CommandHandler {

    Result execute(Request commandRequest);

    Template getTemplate();

    Method getMethod();

    String description();
}
