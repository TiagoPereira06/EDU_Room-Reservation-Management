package pt.isel.ls;

import pt.isel.ls.request.Method;
import pt.isel.ls.request.PathTemplate;
import pt.isel.ls.request.Template;

public class CommandUri {
    static final CommandUri POST_ROOMS_URL =
            new CommandUri(Method.POST, new PathTemplate(Template.ROOMS_CREATE));

    static final CommandUri GET_ROOMS_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.ROOMS));

    static final CommandUri GET_SEARCH_ROOM_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.ROOMS_SEARCH));

    static final CommandUri GET_POST_ROOM_FORM_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.ROOMS_CREATE));

    static final CommandUri GET_POST_USER_FORM_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.USERS_CREATE));

    static final CommandUri GET_POST_LABEL_FORM_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.LABELS_CREATE));

    static final CommandUri GET_ROOM_BY_ID_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.ROOMS_RID));

    static final CommandUri POST_BOOKING_URL =
            new CommandUri(Method.POST, new PathTemplate(Template.ROOMS_RID_BOOKINGS));

    static final CommandUri GET_BOOKING_BY_ROOM_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.ROOMS_RID_BOOKINGS));

    static final CommandUri PUT_BOOKING_URL =
            new CommandUri(Method.PUT, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID));

    static final CommandUri DELETE_BOOKING_URL =
            new CommandUri(Method.DELETE, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID));

    static final CommandUri GET_BOOKING_BY_ID_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.ROOMS_RID_BOOKINGS_BID));

    static final CommandUri POST_USER_URL =
            new CommandUri(Method.POST, new PathTemplate(Template.USERS_CREATE));

    static final CommandUri GET_USER_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.USERS));

    static final CommandUri GET_USER_BY_ID_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.USERS_UID));

    static final CommandUri GET_BOOKING_BY_OWNER =
            new CommandUri(Method.GET, new PathTemplate(Template.USERS_UID_BOOKINGS));

    static final CommandUri GET_BOOKING_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.BOOKINGS));

    static final CommandUri POST_LABEL_URL =
            new CommandUri(Method.POST, new PathTemplate(Template.LABELS_CREATE));

    static final CommandUri GET_LABEL_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.LABELS));

    static final CommandUri GET_LABEL_BY_ID_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.LABELS_LID));

    static final CommandUri GET_ROOM_BY_LABEL_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.LABELS_LID_ROOMS));

    static final CommandUri EXIT_URL =
            new CommandUri(Method.EXIT, new PathTemplate(Template.SLASH));

    static final CommandUri TIME_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.TIME));

    static final CommandUri OPTION_URL =
            new CommandUri(Method.OPTIONS, new PathTemplate(Template.SLASH));

    static final CommandUri INDEX_URL =
            new CommandUri(Method.GET, new PathTemplate(Template.SLASH));

    static final CommandUri LISTEN_URL =
            new CommandUri(Method.LISTEN, new PathTemplate(Template.SLASH));

    public final Method method;
    public final PathTemplate pathTemplate;

    public CommandUri(Method method, PathTemplate pathTemplate) {
        this.method = method;
        this.pathTemplate = pathTemplate;
    }
}
