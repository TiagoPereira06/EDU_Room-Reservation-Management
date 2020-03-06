package pt.isel.ls.utils;

import org.junit.Test;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void checkInsertion() throws SQLException {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);

        //INSERT
        con = ds.getConnection();
        con.setAutoCommit(false);
        Statement st = con.createStatement();
        String insertQuery = "INSERT INTO courses(name) VALUES ('LEIC')";
        st.execute(insertQuery);

        try {
            // //SELECT
            String selectQuery = "SELECT * FROM courses as c WHERE c.name= ?";
            PreparedStatement pst = con.prepareStatement(selectQuery);
            pst.setString(1, "LEIC");
            ResultSet rs = pst.executeQuery();
            assertTrue(rs.next());
        } finally {
            con.rollback();
            con.close();
        }
    }
}
