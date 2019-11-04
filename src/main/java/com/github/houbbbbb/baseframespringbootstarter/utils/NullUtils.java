package com.github.houbbbbb.baseframespringbootstarter.utils;

/**
 * ClassName NullUtils
 * Description TODO
 * Author hbw
 * Date 2019/5/31 15:52
 * Version 1.0
 **/
public class NullUtils {
    public static Boolean isNull(String str) {
        if(null == str || "".equals(str.trim())) return true;
        return false;
    }

    public static Boolean isNull(Object obj) {
        if(null == obj) return true;
        return false;
    }

    public static Boolean isNotNull(String str) {
        return !isNull(str);
    }

    public static Boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static <T> T getNotNull(T t, T s) {
        if(t instanceof String) {
            String o = (String) t;
            return isNotNull(o) ? (T)o : s;
        }
        return isNotNull(t) ? t : s;
    }
}
