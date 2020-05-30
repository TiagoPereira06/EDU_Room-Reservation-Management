package pt.isel.ls.utils;

import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbTests {
    private Connection con;


    @Test
    public void isDbConnectable() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
        try {
            con = dataSource.getConnection();
        } finally {
            con.close();
        }
    }
}
