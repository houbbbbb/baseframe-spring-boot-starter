package com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe;

import com.github.houbbbbb.baseframespringbootstarter.properties.GenerateProperties;
import com.github.houbbbbb.baseframespringbootstarter.utils.FileUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.StrUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.TypeUtils;

import java.util.Map;

/**
 * ClassName GenerateEntity
 * Description 生成实体
 * Author hbw
 * Date 2019/5/31 15:08
 * Version 1.0
 **/
public class GenerateEntity {
    private GenerateProperties generateProperties;

    public GenerateEntity(GenerateProperties generateProperties) {
        this.generateProperties = generateProperties;
    }

    public void create(String table, Map<String, String> map) {
        String className = TypeUtils.getClassName(table);
        StringBuilder sb = new StringBuilder();
        createPackage(sb);
        createClass(className, sb);
        createField(map, sb);
        createSetAndGet(map, sb);
        createToString(map, sb);
        String dirPath = StrUtils.concat(generateProperties.getEnPath(),"/entity/");
        FileUtils.mkDir(dirPath);
        String filePath = dirPath+className+".java";
        FileUtils.writeOne(filePath, sb.toString());
        System.out.println(sb.toString());
    }

    public void createPackage(StringBuilder sb) {
        String pack = StrUtils.concat(generateProperties.getEnPack(),".entity");
        sb.append("package ").append(pack).append(";\n\n");
    }

    public void createClass(String className, StringBuilder sb) {
        sb.append("public class ").append(className).append(" extends BEntity {\n\n");
        sb.append("\tpublic ").append(className).append("() {}\n\n");
    }

    public void createField(Map<String, String> map, StringBuilder sb) {
        if(map.size() > 0) {
            for(Map.Entry<String, String> en: map.entrySet()) {
                sb.append("\tprivate ").append(TypeUtils.sqlType2JavaType(en.getValue()))
                  .append(" ").append(TypeUtils.getFieldName(en.getKey())).append(";\n");
            }
            sb.append("\n");
        }
    }

    public void createSetAndGet(Map<String, String> map, StringBuilder sb) {
        if(map.size() > 0) {
            for(Map.Entry<String, String> en: map.entrySet()) {
                sb.append("\tpublic ").append(TypeUtils.sqlType2JavaType(en.getValue().toString()))
                  .append(" get")
                  .append(TypeUtils.getFieldNameSG(en.getKey())).append("() {\n")
                  .append("\t\treturn ").append(TypeUtils.getFieldName(en.getKey())).append(";\n")
                  .append("\t}\n\n")
                  .append("\tpublic void set")
                  .append(TypeUtils.getFieldNameSG(en.getKey())).append("(")
                  .append(TypeUtils.sqlType2JavaType(en.getValue().toString()))
                  .append(" ").append(TypeUtils.getFieldName(en.getKey()))
                  .append(") {\n")
                  .append("\t\t").append("this.").append(TypeUtils.getFieldName(en.getKey()))
                  .append(" = ").append(TypeUtils.getFieldName(en.getKey())).append(";\n")
                  .append("\t}\n\n");
            }
        }
    }

    public void createToString(Map<String, String> map, StringBuilder sb) {
        if(map.size() > 0) {
            sb.append("\tpublic String toString() {\n")
              .append("\t\t").append("JSONObject jsonObject = new JSONObject();\n");
            for(Map.Entry<String, String> en: map.entrySet()) {
                sb.append("\t\tjsonObject.put(\"").append(TypeUtils.getFieldName(en.getKey())).append("\"")
                  .append(", ").append(TypeUtils.getFieldName(en.getKey())).append(");\n");
            }
            sb.append("\t\t").append("return jsonObject.toString();\n");
            sb.append("\t}\n").append("}");
        }
    }
}
