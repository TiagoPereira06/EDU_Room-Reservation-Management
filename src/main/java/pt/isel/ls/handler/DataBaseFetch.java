package pt.isel.ls.handler;

import java.sql.Connection;

public interface DataBaseFetch {
    CommandResult execute(Connection connection) throws Exception;
}
