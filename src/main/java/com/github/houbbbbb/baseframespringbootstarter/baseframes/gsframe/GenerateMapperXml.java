package com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe;

import com.github.houbbbbb.baseframespringbootstarter.properties.GenerateProperties;
import com.github.houbbbbb.baseframespringbootstarter.utils.FileUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.StrUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.TypeUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ClassName GenerateMapperXml
 * Description 生成mapper.xml
 * Author hbw
 * Date 2019/5/31 15:09
 * Version 1.0
 **/
@Component
public class GenerateMapperXml {
    private GenerateProperties generateProperties;

    public GenerateMapperXml(GenerateProperties generateProperties) {
        this.generateProperties = generateProperties;
    }

    public void create(Map<String, String> map, String table) {
        String className = TypeUtils.getClassName(table);
        String mapperName = className+"Mapper";
        String pack = generateProperties.getEnPack();
        String enPack = StrUtils.concat(pack, ".entity");
        String maPack = StrUtils.concat(pack, ".mapper");
        String entityAll = enPack+"."+className;
        String mapperAll = maPack+"."+mapperName;
        StringBuilder sb = new StringBuilder();
        List<String> cols = getCols(map);
        creatHeader(sb);
        createNameSpace(mapperAll, sb);
        if(null != cols && cols.size() > 0) {
            createBody(table, entityAll, sb, cols);
        }
        String mapperXmlPath = generateProperties.getEnMPath()+"/"+mapperName+".xml";
        FileUtils.writeOne(mapperXmlPath, sb.toString());
    }

    public List<String> getCols(Map<String, String> map) {
        if(null!=map && map.size()>0) {
            List<String> list = new ArrayList<>();
            for(Map.Entry<String,String> en: map.entrySet()) {
                String name = en.getKey();
                list.add(name);
            }
            return list;
        }
        return null;
    }

    public void creatHeader(StringBuilder sb) {
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<!DOCTYPE mapper PUBLIC\n");
        sb.append("\t\"-//mybatis.org//DTD Mapper 3.0//EN\"\n");
        sb.append("\t\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
    }

    public void createNameSpace(String mapperAll, StringBuilder sb) {
        sb.append("<mapper namespace=").append("\"").append(mapperAll).append("\" >\n\n");
    }

    public void createBody(String table, String entityAll, StringBuilder sb, List<String> cols) {
        createInsert(table, entityAll, sb, cols);
        createUpdate(table, entityAll, sb, cols);
        createDelete(table, entityAll, sb, cols);
        createSelect(table, entityAll, sb, cols);
        sb.append("\n</mapper>");
    }

    public void createInsert(String table, String entityAll, StringBuilder sb, List<String> cols) {
        int colS1 = generateProperties.getXmlcol1();
        int colS2 = generateProperties.getXmlcol2();
        int colSize = cols.size();
        sb.append("\t<insert id=\"insertOne\" parameterType=\"")
          .append(entityAll).append("\" >\n");
        sb.append("\t\tinsert into ").append(table).append(" (\n");
        purColWrite(sb, cols, colS1, colSize);
        sb.append("\n");
        sb.append("\t\t) values (\n");
        jColWrite(sb, cols, colS2, colSize);
        sb.append("\n");
        sb.append("\t\t)\n\t</insert>\n\n");

        sb.append("\t<insert id=\"insertAll\" parameterType=\"java.util.List\" >\n")
          .append("\t\tinsert into ").append(table).append(" (\n");
        purColWrite(sb, cols, colS1, colSize);
        sb.append("\n");
        sb.append("\t\t) values\n");
        sb.append("\t\t").append("<foreach collection=\"list\" item=\"item\" separator=\",\">\n");
        jColWriteAll(sb, cols, colS2, colSize);
        sb.append("\n\t\t</foreach>");
        sb.append("\n\t</insert>\n\n");

    }

    public void createUpdate(String table, String entityAll, StringBuilder sb, List<String> cols) {
        sb.append("\t<update id=\"update\" parameterType=\"").append(entityAll).append("\" >\n");
        sb.append("\t\tupdate ").append(table).append(" <set> \n");
        ifColWriteUpdate(sb, cols);
        sb.append("\t\t</set>\n");
        sb.append("\t\t<where>\n");
        ifColWrite(sb, cols);
        sb.append("\t\t</where>");
        sb.append("\n\t</update>\n\n");
    }

    public void createDelete(String table, String entityAll, StringBuilder sb, List<String> cols) {
        sb.append("\t<delete id=\"delete\" parameterType=\"").append(entityAll).append("\" >\n");
        sb.append("\t\tdelete from ").append(table).append(" <where>\n");
        ifColWrite(sb, cols);
        sb.append("\t\t</where>");
        sb.append("\n\t</delete>\n\n");
    }

    public void createSelect(String table, String entityAll, StringBuilder sb, List<String> cols) {
        int colS1 = generateProperties.getXmlcol1();
        int colSize = cols.size();
        selectWrite(table, entityAll, sb, cols, colS1, colSize, "selectOne", 1);
        selectWrite(table, entityAll, sb, cols, colS1, colSize, "selectAll", 1);
        selectWrite(table, entityAll, sb, cols, colS1, colSize, "selectCount", 2);
    }

    private void selectWrite(String table, String entityAll, StringBuilder sb, List<String> cols
            , int colS1, int colSize, String method, int flag) {
        String reType = null;
        if(1 == flag) reType = entityAll;
        else if(2 == flag) reType = "java.lang.Long";
        sb.append("\t<select id=\"").append(method).append("\"  parameterType=\"")
          .append(entityAll).append("\" ")
          .append("resultType=\"").append(reType).append("\" >\n");
        sb.append("\t\tselect \n");
        if(1 == flag) purColWrite(sb, cols, colS1, colSize);
        else if(2 == flag) sb.append("\t\t\tcount(1) ");
        sb.append("\n\t\tfrom ").append(table).append(" <where>\n");
        ifColWrite(sb, cols);
        sb.append("\t\t</where>");
        sb.append("\n\t</select>\n\n");
    }

    private void ifColWrite(StringBuilder sb, List<String> cols) {
        for(String col: cols) {
            sb.append("\t\t\t").append("<if test=\"").append("null!=").append(TypeUtils.getFieldName(col))
                    .append("\">").append(" and ")
                    .append("`").append(col).append("`")
              .append(" = ").append("#{").append(TypeUtils.getFieldName(col)).append("}</if>\n");
        }
    }

    private void ifColWriteUpdate(StringBuilder sb, List<String> cols) {
        for(String col: cols) {
            sb.append("\t\t\t").append("<if test=\"").append("null!=").append(TypeUtils.getFieldName(col))
                    .append("\">")
                    .append("`").append(col).append("`")
              .append(" = ").append("#{").append(TypeUtils.getFieldName(col)).append("}, </if>\n");
        }
    }

    private void jColWrite(StringBuilder sb, List<String> cols, int colS2, int colSize) {
        int j = 0;
        for(String col: cols) {
            if(j == 0) {
                sb.append("\t\t\t").append("#{").append(TypeUtils.getFieldName(col)).append("},");
            } else if((j+1) % colS2 == 0) {
                sb.append("#{").append(TypeUtils.getFieldName(col)).append("}");
                sb.append("\n\t\t\t,");
            } else {
                sb.append("#{").append(TypeUtils.getFieldName(col)).append("},");
            }
            ++j;
        }
        deleteTail(sb, colS2, colSize);
    }

    private void jColWriteAll(StringBuilder sb, List<String> cols, int colS2, int colSize) {
        sb.append("\t\t\t").append("(");
        int j = 0;
        for(String col: cols) {
            if(j == 0) {
                sb.append("#{item.").append(TypeUtils.getFieldName(col)).append("},");
            } else if((j+1) % colS2 == 0) {
                sb.append("#{item.").append(TypeUtils.getFieldName(col)).append("}");
                sb.append("\n\t\t\t,");
            } else {
                sb.append("#{item.").append(TypeUtils.getFieldName(col)).append("},");
            }
            ++j;
        }
        deleteTail(sb, colS2, colSize);
        sb.append(")");
    }

    private void purColWrite(StringBuilder sb, List<String> cols, int colS1, int colSize) {
        int i = 0;
        for(String col: cols) {
            if(i == 0) {
                sb.append("\t\t\t`").append(col).append("`,");
            } else if((i+1) % colS1 == 0) {
                sb.append("`").append(col).append("`\n\t\t\t,");
            } else {
                sb.append("`").append(col).append("`,");
            }
            ++i;
        }
        deleteTail(sb, colS1, colSize);
    }

    private void deleteTail(StringBuilder sb, int colS, int colSize) {
        if (colSize % colS == 0) sb.delete(sb.length() - 5, sb.length());
        else sb.delete(sb.length() - 1, sb.length());
    }
}
