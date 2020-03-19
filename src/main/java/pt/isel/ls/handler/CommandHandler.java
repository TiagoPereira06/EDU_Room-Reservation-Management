package pt.isel.ls.handler;

import pt.isel.ls.request.CommandRequest;

import java.util.HashMap;
import java.util.LinkedList;


public interface CommandHandler {
    final String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";

    CommandResult execute(CommandRequest commandRequest);

    String description();
}
