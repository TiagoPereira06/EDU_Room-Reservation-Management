package pt.isel.ls.request;

public class Template {

    public static final String OPTION = "/";

    public static final String ROOMS = "/rooms";

    public static final String USERS = "/users";

    public static final String LABELS = "/labels";

    public static final String BOOKINGS = "/bookings";

    public static final String ROOMS_RID = "/rooms/{rid}";

    public static final String USERS_UID = "/users/{uid}";

    public static final String USERS_UID_BOOKINGS = "/users/{uid}/bookings";

    public static final String ROOMS_RID_BOOKINGS_BID = "/rooms/{rid}/bookings/{bid}";

    public static final String ROOMS_RID_BOOKINGS = "/rooms/{rid}/bookings";

    public static final String LABELS_LID_ROOMS = "/labels/{lid}/rooms";

}
