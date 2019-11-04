package com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * ClassName GenerateRoot
 * Description 生成
 * Author hbw
 * Date 2019/7/11 15:54
 * Version 1.0
 **/
public class GenerateRoot {
    private Connection connection;

    public GenerateRoot(Connection connection) {
        this.connection = connection;
    }

    public Map<String, String> getMap(String table) {
        try {
            String sql = "select * from "+table;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
            int size = resultSetMetaData.getColumnCount();
            if(size > 0) {
                Map<String, String> map = new TreeMap<>();
                for (int i = 1; i <= size; i++) {
                    String colName = resultSetMetaData.getColumnName(i);
                    String colTypeName = resultSetMetaData.getColumnTypeName(i);
                    if(!"id".equalsIgnoreCase(colName)) {
                        map.put(colName, colTypeName);
                    }
                }
                return map;
            }
        } catch (Exception e) {e.printStackTrace();}
        return null;
    }
}
