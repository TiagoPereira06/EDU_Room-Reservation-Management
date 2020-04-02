package pt.isel.ls.utils.handlers;

import org.junit.Test;
import pt.isel.ls.App;

public class RoomsTest {
    String[] args = null;

    @Test
    public void getRoomsTest() {
        args = new String[]{"GET", "rooms"};
        App.main(args);
    }

    @Test
    public void getRoomsByIdTest() {
        args = new String[]{"GET", "rooms/LS2"};
        App.main(args);
    }

    @Test
    public void getRoomsByIdTest2() {

        args = new String[]{"GET", "rooms/LAC"};
        App.main(args);
    }

    @Test
    public void getRoomsByIdTest3() {

        args = new String[]{"GET", "rooms/LH1"};
        App.main(args);
    }
}
