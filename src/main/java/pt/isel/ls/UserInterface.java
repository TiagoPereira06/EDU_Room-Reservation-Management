package pt.isel.ls;

import pt.isel.ls.handler.CommandResult;

public class UserInterface {
    public void show(CommandResult commandResult) {
        for (Object obj : commandResult.getResult()) {
            System.out.print(obj.toString());
        }
    }
}
