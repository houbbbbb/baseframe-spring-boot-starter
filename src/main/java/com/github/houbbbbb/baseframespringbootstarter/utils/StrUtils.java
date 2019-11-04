package com.github.houbbbbb.baseframespringbootstarter.utils;

public class StrUtils {

    public static String concat(String a, String b) {
        return a.concat(b);
    }

    public static String append(String...strs) {
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
