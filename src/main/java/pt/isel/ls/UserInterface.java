package pt.isel.ls;

import pt.isel.ls.handler.CommandResult;

public class UserInterface {
    public void show(CommandResult commandResult) {
        System.out.println("\n --CommandResult--");
        for (Object obj : commandResult.getResult()) {
            System.out.println(obj.toString());
        }
    }

    public void show(String s) {
        System.out.println("\n --CommandResult--");
        System.out.println(s);
    }


    public void askForCommand() {
        System.out.println("\nEnter a command:");
        System.out.println("{Method} {Path} {Arguments}");
        System.out.print("-> ");
    }
}
