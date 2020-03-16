package pt.isel.ls.handler.user;

import pt.isel.ls.handler.CommandHandler;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.request.CommandRequest;
import pt.isel.ls.request.Method;
import pt.isel.ls.request.Template;

public class PostUser implements CommandHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) {
        return null;
    }

    @Override
    public Template getTemplate() {
        return Template.where(Template.USERS);
    }

    @Override
    public String description() {
        return "Creates new user";
    }


}
