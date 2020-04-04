package pt.isel.ls.utils;

import pt.isel.ls.request.Parameter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UtilMethods {

    public static List<Parameter> getParameters(String[] rawString) {
        List<Parameter> list = new LinkedList<>();
        if (rawString.length <= 2) {
            return list;
        }
        String rawParam = rawString[2];
        String[] params = rawParam.split("&");
        for (String st : params) {
            String[] parts = st.split("=");
            list.add(new Parameter(parts[0], parts[1]));
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

}
