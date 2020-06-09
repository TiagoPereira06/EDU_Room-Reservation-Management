package pt.isel.ls.handler;

import java.sql.Connection;

public interface DataBaseFetch {
    ResultView execute(Connection connection) throws Exception;
}
