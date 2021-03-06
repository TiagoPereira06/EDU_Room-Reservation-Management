package pt.isel.ls.utils;

import pt.isel.ls.model.Booking;
import pt.isel.ls.request.Parameter;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilMethods {

    public static String[] filterStringArray(String[] array) {
        return Arrays.stream(array)
                .filter(filtered -> (filtered != null && filtered.length() > 0))
                .toArray(String[]::new);
    }

    public static List<Parameter> concatTwoLists(List<Parameter> list1, List<Parameter> list2) {
        return Stream.concat(list1.stream(), list2.stream())
                .collect(Collectors.toList());
    }

    public static Date formatStringToDate(String date) throws ParseException {
        if (date.split(":").length == 2) {
            date = date.concat(":00");
        }
        return Booking.dateFormat.parse(date);
    }

    public static String formatStringDate(String date) {
        if (date.split(":").length == 2) {
            date = date.concat(":00");
        }
        return date;
    }

    public static String formatDateToString(Date date) {
        return Booking.dateFormat.format(date);
    }

    public static byte[] getBytes(String target) {
        return target.getBytes(StandardCharsets.UTF_8);
    }

    public static void checkValid(String value, String tag) throws NoSuchElementException {
        if (value == null || value.equals("0") || value.equals("[]")) {
            throw new NoSuchElementException(tag);
        }
    }

    public static String fixPath(String value) {
        return value.replace(" ", "%20");
    }

}
