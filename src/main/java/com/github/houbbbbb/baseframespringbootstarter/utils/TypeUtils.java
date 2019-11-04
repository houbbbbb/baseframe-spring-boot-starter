package com.github.houbbbbb.baseframespringbootstarter.utils;

/**
 * ClassName TypeUtils
 * Description TODO
 * Author hbw
 * Date 2019/5/31 15:55
 * Version 1.0
 **/
public class TypeUtils {
    public static String sqlType2JavaType(String sqlType) {
        if(NullUtils.isNotNull(sqlType)) {
            if(equalIgnoreCaseOr(sqlType, "TINYINT", "Byte")) {
                return "Byte";
            } else if(equalIgnoreCaseOr(sqlType, "SMALLINT", "Short")) {
                return "Short";
            } else if(equalIgnoreCaseOr(sqlType, "MEDIUMINT"
                    ,"INTEGER","INT")) {
                return "Integer";
            } else if(equalIgnoreCaseOr(sqlType, "BIGINT", "Long")) {
                return "Long";
            } else if(equalIgnoreCaseOr(sqlType, "REAL")) {
                return "Float";
            } else if(equalIgnoreCaseOr(sqlType, "FLOAT",
                    "DOUBLE")) {
                return "Double";
            } else if(equalIgnoreCaseOr(sqlType, "BIT"
                    ,"BOOLEAN","BOOL")) {
                return "Boolean";
            } else if(equalIgnoreCaseOr(sqlType, "VARCHAR"
                    ,"CHAR","TEXT","LONGVARCHAR","String","MEDIUMTEXT")) {
                return "String";
            } else if(equalIgnoreCaseOr(sqlType, "YEAR"
                    ,"DATE", "TIMESTAMP", "DATETIME")) {
                return "Date";
            } else if(equalIgnoreCaseOr(sqlType, "TIME")) {
                return "Time";
            } else if(equalIgnoreCaseOr(sqlType, "DECIMAL",
                    "NUMERIC", "BigDecimal")) {
                return "BigDecimal";
            } else if(equalIgnoreCaseOr(sqlType, "BIGINT",
                    "BigInteger")) {
                return "BigInteger";
            } else if(equalIgnoreCaseOr(sqlType, "BLOB")) {
                return "Blob";
            } else if(equalIgnoreCaseOr(sqlType, "BINARY"
                    ,"VARBINARY","LONGVARBINARY", "byte[]")) {
                return "byte[]";
            } else if(equalIgnoreCaseOr(sqlType,"CLOB")) {
                return "Clob";
            } else if(equalIgnoreCaseOr(sqlType,"ARRAY")) {
                return "Array";
            }
        }

        return sqlType;
    }

    public static Boolean equalIgnoreCaseOr(String str, String...strs) {
        boolean b = false;
        if(NullUtils.isNotNull(str) && null!=strs && strs.length>0) {
            for (String s : strs) {
               b = b || str.equalsIgnoreCase(s);
            }
        }
        return b;
    }

    public static String firstUpper(String str) {
        if(NullUtils.isNotNull(str)) {
            char[] chars = str.toCharArray();
            if(chars[0] >= 'a' && chars[0] <= 'z') {
                chars[0] = (char) (chars[0] - 32);
            }
            return new String(chars);
        }
        return str;
    }

    public static String firstLower(String str) {
        if(NullUtils.isNotNull(str)) {
            char[] chars = str.toCharArray();
            if(chars[0] >= 'A' && chars[0] <= 'Z') {
                chars[0] = (char) (chars[0] + 32);
            }
            return new String(chars);
        }
        return str;
    }

    public static String getClassName(String table) {
        String className = null;
        if(NullUtils.isNotNull(table)) {
            StringBuilder sb = new StringBuilder();
            if(table.contains("_")) {
                String[]  strs = table.split("_");
                for(String str: strs) {
                    sb.append(firstUpper(str));
                }
                className = sb.toString();
            } else {
                className = firstUpper(table);
            }
        }
        return className;
    }

    public static String getFieldName(String fieldName) {
        return firstLower(getClassName(fieldName));
    }

    public static String getFieldNameSG(String fieldName) {
        return getClassName(fieldName);
    }
}
