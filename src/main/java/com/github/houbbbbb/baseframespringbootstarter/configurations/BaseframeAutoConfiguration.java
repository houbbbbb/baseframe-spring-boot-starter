package com.github.houbbbbb.baseframespringbootstarter.configurations;

import com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe.*;
import com.github.houbbbbb.baseframespringbootstarter.properties.GenerateProperties;
import com.github.houbbbbb.baseframespringbootstarter.properties.JDBCProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ClassName BaseframeAutoConfiguration
 * Description 基本配置
 * Author hbw
 * Date 2019/10/25 11:34
 * Version 1.0
 **/
@Configuration
@EnableConfigurationProperties({GenerateProperties.class, JDBCProperties.class})
public class BaseframeAutoConfiguration {

    @Bean
    public GenerateOpt getGenerateOpt(GenerateProperties generateProperties, JDBCProperties jdbcProperties) {
        return new GenerateOpt(
                getGenerateRoot(jdbcProperties)
                , getGenerateEntity(generateProperties)
                , getGenerateMapper(generateProperties)
                , getGenerateMapperXml(generateProperties)
                , getGenerateService(generateProperties));
    }

    private GenerateEntity getGenerateEntity(GenerateProperties generateProperties) {
        return new GenerateEntity(generateProperties);
    }

    private GenerateMapper getGenerateMapper(GenerateProperties generateProperties) {
        return new GenerateMapper(generateProperties);
    }

    private GenerateMapperXml getGenerateMapperXml(GenerateProperties generateProperties) {
        return new GenerateMapperXml(generateProperties);
    }

    private GenerateService getGenerateService(GenerateProperties generateProperties) {
        return new GenerateService(generateProperties);
    }

    private GenerateRoot getGenerateRoot(JDBCProperties jdbcProperties) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    jdbcProperties.getUrl()
                    , jdbcProperties.getUserName()
                    , jdbcProperties.getPassword());

            return new GenerateRoot(connection);
        } catch (Exception e) {
            return null;
        }
    }
}
