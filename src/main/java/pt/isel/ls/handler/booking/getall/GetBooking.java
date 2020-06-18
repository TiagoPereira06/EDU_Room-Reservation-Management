package pt.isel.ls.handler.booking.getall;

import pt.isel.ls.handler.CommandResult;
import pt.isel.ls.handler.booking.BookingHandler;
import pt.isel.ls.model.Booking;
import pt.isel.ls.request.CommandRequest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;


public class GetBooking extends BookingHandler {
    @Override
    public CommandResult execute(CommandRequest commandRequest) throws Exception {
        return commandRequest.transactionManager.execute((connection) -> {
            String getBookingsQuery = "SELECT * FROM bookings";
            PreparedStatement statement = connection.prepareStatement(getBookingsQuery);
            ResultSet resultSet = statement.executeQuery();
            List<Booking> getAllBookingResult = new LinkedList<>();
            while (resultSet.next()) {
                Booking b = new Booking(
                        resultSet.getInt("bid"),
                        resultSet.getString("reservationOwner"),
                        resultSet.getString("roomName"),
                        resultSet.getString("beginTime"),
                        resultSet.getString("endTime"));
                getAllBookingResult.add(b);
            }
            return new GetBookingResult(getAllBookingResult);
        });
    }


    @Override
    public String description() {
        return "Show all bookings";
    }
}
