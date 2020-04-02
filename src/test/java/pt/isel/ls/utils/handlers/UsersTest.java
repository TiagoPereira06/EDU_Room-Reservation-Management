package pt.isel.ls.utils.handlers;

import org.junit.Test;
import pt.isel.ls.App;

public class UsersTest {
    String[] args = null;

    @Test
    public void getUserTest() {
        args = new String[]{"GET", "users"};
        App.main(args);
    }

    @Test
    public void getUserByUidTest() {
        args = new String[]{"GET", "users/rubenmaster@slb.pt"};
        App.main(args);

    }

    @Test
    public void getUserByUidTest2() {
        args = new String[]{"GET", "users/adel15220@live.com.pt"};
        App.main(args);

    }

    @Test
    public void getUserTest3() {
        args = new String[]{"GET", "users/ttavares@slb.pt/bookings"};
        App.main(args);
    }
}
