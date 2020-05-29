package pt.isel.ls.utils;

import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbTests {
    private final String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";
    private Connection con;
    private PGSimpleDataSource ds;


    @Test
    public void isDbConnectable() throws SQLException {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        try {
            con = ds.getConnection();
        } finally {
            con.close();
        }
    }
}
