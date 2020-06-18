package pt.isel.ls.request;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.errors.database.InternalDataBaseException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.DataBaseFetch;

import java.sql.Connection;

public class TransactionManager {
    private final PGSimpleDataSource dataSource;

    public TransactionManager() {
        dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
    }

    public CommandResult execute(DataBaseFetch command) throws Exception {
        Connection connection = null;
        CommandResult resultCommandResult;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            resultCommandResult = command.execute(dataSource.getConnection());
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
                connection.close();
                throw e;
            } else {
                throw new InternalDataBaseException();
            }
        } finally {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return resultCommandResult;
    }
}

