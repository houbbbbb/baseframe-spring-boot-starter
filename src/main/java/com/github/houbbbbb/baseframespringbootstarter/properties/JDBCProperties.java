package com.github.houbbbbb.baseframespringbootstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ClassName JDBCProperties
 * Description TODO
 * Author hbw
 * Date 2019/10/29 12:01
 * Version 1.0
 **/
@ConfigurationProperties(prefix = "spring.datasource")
public class JDBCProperties {
    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
