package pt.isel.ls.utils.handlers;


public class GetUserTest {
    /*private final String url = "jdbc:postgresql://localhost:5432/school?user=postgres&password=1234";
    private Connection con;
    private PGSimpleDataSource ds;

    @Test
    public void getUserByIdTest() throws SQLException {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);

        try {

            String selectQuery = "SELECT * FROM users as u"
                    + " WHERE u.email = ?";
            PreparedStatement pst = con.prepareStatement(selectQuery);
            pst.setString(1, "adel15220@live.com.pt");
            ResultSet rs = pst.executeQuery();

            assertTrue(rs.next());
        } finally {
            con.rollback();
            con.close();
        }
    }*/

    // @Test
   /* public void getUserTest() throws SQLException {
        ds = new PGSimpleDataSource();
        ds.setUrl(url);
        CommandResult commandResult = new CommandResult();

        try {

            GetUser us1 = null;
            Path path = new Path("/users");
           LinkedList<Parameter> param = new LinkedList<>();
            Parameter p1 = new Parameter("name", "Adelaide Sousa");
            Parameter p2 = new Parameter("email", "adel15220@live.com.pt");
            param.add(p1);
            param.add(p2);
            CommandRequest cReq = new CommandRequest(Method.GET, path, null);
            CommandResult cRes = us1.execute(cReq);

            User u1 = new User("adel15220@live.com.pt", "Adelaide Sousa");
            LinkedList<User> list = new LinkedList<>();
            list.add(u1);
            LinkedList<User> actual = new LinkedList<>();


            Assert.assertEquals(list.get(0).getEmail(), cRes.getResult().get(0));
            Assert.assertEquals(list.get(1).getName(), cRes.getResult().get(1));


        } finally {
            con.rollback();
            con.close();
        }
    }*/
}

