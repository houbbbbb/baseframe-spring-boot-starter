package com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe;

import com.github.houbbbbb.baseframespringbootstarter.properties.GenerateProperties;
import com.github.houbbbbb.baseframespringbootstarter.utils.FileUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.StrUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.TypeUtils;
import org.springframework.stereotype.Component;

/**
 * ClassName GenerateMapper
 * Description 生成mapper
 * Author hbw
 * Date 2019/6/1 10:42
 * Version 1.0
 **/
@Component
public class GenerateMapper {
    private GenerateProperties generateProperties;

    public GenerateMapper(GenerateProperties generateProperties) {
        this.generateProperties = generateProperties;
    }

    public void create(String table) {
        String className = TypeUtils.getClassName(table);
        String mapperName = className+"Mapper";
        StringBuilder sb = new StringBuilder();
        createHeader(className, mapperName, sb);
        String dirPath = StrUtils.concat(generateProperties.getEnPath(), "/mapper/");
        FileUtils.mkDir(dirPath);
        String mapperPath = dirPath+mapperName+".java";
        FileUtils.writeOne(mapperPath, sb.toString());
    }

    public void createHeader(String className, String mapperName, StringBuilder sb) {
        String pack = StrUtils.concat(generateProperties.getEnPack(), ".mapper");
        sb.append("package ").append(pack).append(";\n\n");
        sb.append("@Repository\n"); // .append(TypeUtils.firstLower(mapperName)).append("\")\n");
        sb.append("public interface ").append(mapperName)
          .append(" extends IDao").append(" {\n\n");
        sb.append("}");
    }
}
