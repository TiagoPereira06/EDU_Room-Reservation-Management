package pt.isel.ls.request;

public class Template {

    public static final String SLASH = "/";

    public static final String TIME = "/time";

    public static final String ROOMS = "/rooms";

    public static final String USERS = "/users";

    public static final String LABELS = "/labels";

    public static final String LABELS_LID = "/labels/{lid}";

    public static final String BOOKINGS = "/bookings";

    public static final String ROOMS_RID = "/rooms/{rid}";

    public static final String ROOMS_SEARCH = "/rooms/search";

    public static final String USERS_UID = "/users/{uid}";

    public static final String USERS_UID_BOOKINGS = "/users/{uid}/bookings";

    public static final String ROOMS_RID_BOOKINGS_BID = "/rooms/{rid}/bookings/{bid}";

    public static final String ROOMS_RID_BOOKINGS = "/rooms/{rid}/bookings";

    public static final String ROOMS_CREATE = "/rooms/create";

    public static final String USERS_CREATE = "/users/create";

    public static final String LABELS_CREATE = "/labels/create";

    public static final String BOOKING_CREATE = "/rooms/{rid}/bookings/create";

    public static final String LABELS_LID_ROOMS = "/labels/{lid}/rooms";

}
