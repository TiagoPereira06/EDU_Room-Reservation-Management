package pt.isel.ls.utils;

import pt.isel.ls.model.Booking;
import pt.isel.ls.request.Parameter;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilMethods {

    public static List<Parameter> getParameters(String[] rawInput, int index) {
        List<Parameter> list = new LinkedList<>();
        if (index >= rawInput.length) {
            return list;
        }
        String rawString = rawInput[index];
        String[] params = rawString.split("&");
        for (String st : params) {
            String[] parts = st.split("=");
            if (parts.length > 1) {
                list.add(new Parameter(parts[0], parts[1]));
            }
        }
        return list;
    }

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
        return Booking.dateFormat.parse(date);
    }

    public static String formatDateToString(Date endTime) {
        return Booking.dateFormat.format(endTime);
    }

    public static byte[] getBytes(String target) {
        return target.getBytes(StandardCharsets.UTF_8);
    }

}
