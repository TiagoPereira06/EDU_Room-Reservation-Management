package pt.isel.ls.utils;

import pt.isel.ls.request.Method;

public class UtilMethods {
    public static Method checkMethod(String arg) {
        return Method.valueOf(arg);
    }

}
