package pt.isel.ls.handler;

public class Parameters {
    public static final class BookingId {
        public static final String BOOKING_ID = "bid";

        public static String checkId(String bid) throws Exception {
            if (bid == null) {
                String message = "Missing a parameter";
                throw new Exception(message);
            }
            if (bid.chars().allMatch(Character::isLetter)) {
                return bid;
            }
            String message = "Parameter " + bid + " is invalid";
            throw new Exception(message);
        }
    }

    public static final class Email {
        public static final String EMAIL = "email";

        public static String checkEmail(String email) throws Exception {
            if (email == null) {
                String message = "Missing a parameter";
                throw new Exception(message);
            }
            if (email.contains("@")) {
                return email;
            }
            String message = "Parameter " + email + "is invalid";
            throw new Exception(message);
        }

    }
}
