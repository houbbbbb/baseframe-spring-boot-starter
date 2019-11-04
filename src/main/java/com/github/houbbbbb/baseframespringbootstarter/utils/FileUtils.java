package com.github.houbbbbb.baseframespringbootstarter.utils;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * ClassName FileUtils
 * Description TODO
 * Author hbw
 * Date 2019/6/1 10:20
 * Version 1.0
 **/
public class FileUtils {
    public static void writeOne(String filePath, String content) {
        PrintWriter printWriter = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(filePath));
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream, EncodingUtils.UTF_8);
            printWriter = new PrintWriter(new File(filePath));
            printWriter.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != printWriter) printWriter.close();
            try{if(null != fileOutputStream) fileOutputStream.close();}catch(Exception e){e.printStackTrace();}
        }
    }
    public static Set<String> readSetLn(String filePath) {

        Set<String> set = new HashSet<>();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(filePath));
            InputStreamReader reader = new InputStreamReader(fileInputStream, EncodingUtils.UTF_8);
            bufferedReader = new BufferedReader(reader);
            String str = null;
            while (null != (str = bufferedReader.readLine())) {
                if(NullUtils.isNotNull(str)) {
                    set.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{if(null != bufferedReader) bufferedReader.close();}catch(Exception e){e.printStackTrace();}
            try{if(null != fileInputStream) fileInputStream.close();}catch(Exception e){e.printStackTrace();}
        }
        return set;
    }

    public static void mkDir(String path) {
        File f = new File(path);
        if(!(f.exists() && f.isDirectory())) {
            f.mkdir();
        }
    }
}
