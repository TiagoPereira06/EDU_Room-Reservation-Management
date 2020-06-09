package pt.isel.ls.request;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.handler.DataBaseFetch;
import pt.isel.ls.handler.ResultView;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final PGSimpleDataSource dataSource;

    public TransactionManager() {
        dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
    }

    public ResultView execute(DataBaseFetch command) throws Exception {
        Connection connection = null;
        ResultView resultCommandResult;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            resultCommandResult = command.execute(dataSource.getConnection());
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
                connection.close();
                throw new Exception(e);
            } else {
                throw new SQLException("Database is Unreachable");
            }
        }
        return resultCommandResult;
    }
}

