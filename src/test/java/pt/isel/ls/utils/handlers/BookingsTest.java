package pt.isel.ls.utils.handlers;

import org.junit.Test;
import pt.isel.ls.App;

public class BookingsTest {
    String[] args = null;

    @Test
    public void getBookingsTest() {
        args = new String[]{"GET", "bookings"};
        App.main(args);

    }

    @Test
    public void getBookingsByIdTest() {
        args = new String[]{"GET", "rooms/LH1/bookings/4"};
        App.main(args);
    }

    @Test
    public void getBookingsByIdTest2() {
        args = new String[]{"GET", "rooms/LS2/bookings/2"};
        App.main(args);
    }

    @Test
    public void getBookingsByIdTest3() {
        args = new String[]{"GET", "rooms/LH1/bookings/1"};
        App.main(args);
    }
}
