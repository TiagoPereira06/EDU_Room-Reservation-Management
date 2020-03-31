package pt.isel.ls.utils.handlers;


public class GetRoomTest {
   /* private final String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";
    private Connection con;
    private PGSimpleDataSource ds;

    @Test
    public void getRoomsByLabelTest() throws SQLException {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);

        try {
            String selectQuery = "SELECT r.name,r.location,r.capacity,r.description from rooms as r "
                    + "INNER JOIN roomlabels as rm ON r.name = rm.roomName WHERE rm.label = ?";
            PreparedStatement pst = con.prepareStatement(selectQuery);
            pst.setString(1, "easy access");
            ResultSet rs = pst.executeQuery();

            assertTrue(rs.next());
        } finally {
            con.rollback();
            con.close();
        }
    }

    //@Test
    public void getRoomsByIdTest() throws SQLException {
       ds = new PGSimpleDataSource();
        ds.setUrl(url);

        try {
            String selectQuery = "SELECT * from rooms WHERE name = ?";
            PreparedStatement pst = con.prepareStatement(selectQuery);
            pst.setString(1, "LH1");
            ResultSet rs = pst.executeQuery();

            assertTrue(rs.next());
        } finally {
            con.rollback();
            con.close();
        }
    }*/
}
