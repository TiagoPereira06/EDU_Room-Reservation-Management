package pt.isel.ls.handler;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;

public class TransactionManager {
    private final PGSimpleDataSource dataSource;

    public TransactionManager() {
        dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
    }

    public Model execute(DataBaseCommand command) throws Exception {
        Connection connection = null;
        Model resultModel = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            resultModel = command.execute(dataSource.getConnection());
            connection.commit();
        } catch (Exception e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } finally {
                if (connection != null) {
                    connection.close();
                }
            }
        }
        return resultModel;
    }
}
