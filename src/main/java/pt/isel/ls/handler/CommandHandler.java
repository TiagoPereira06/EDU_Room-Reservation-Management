package pt.isel.ls.handler;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.request.CommandRequest;


public interface CommandHandler {
    String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";
    PGSimpleDataSource dataSource = new PGSimpleDataSource();

    CommandResult execute(CommandRequest commandRequest);

    String description();
}
