package pt.isel.ls.handler;

import java.sql.Connection;

public interface DataBaseCommand {
    Model execute(Connection connection) throws Exception;
}
