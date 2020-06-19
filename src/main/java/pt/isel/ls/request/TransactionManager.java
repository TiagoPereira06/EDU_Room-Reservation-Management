package pt.isel.ls.request;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.errors.command.CommandException;
import pt.isel.ls.errors.command.InternalDataBaseException;
import pt.isel.ls.errors.command.ResultNotFoundException;
import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.DataBaseFetch;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private final PGSimpleDataSource dataSource;

    public TransactionManager() {
        dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
    }

    public CommandResult execute(DataBaseFetch command) throws CommandException {
        Connection connection = null;
        CommandResult resultCommandResult;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            resultCommandResult = command.execute(dataSource.getConnection());
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    connection.close();
                } catch (SQLException ex) {
                    throw new InternalDataBaseException();
                }
                if (e instanceof CommandException) {
                    throw (CommandException) e;
                } else {
                    throw new ResultNotFoundException();
                }
            } else {
                throw new InternalDataBaseException("Database is Unreachable");
            }
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                throw new InternalDataBaseException();
            }
        }
        return resultCommandResult;
    }
}

