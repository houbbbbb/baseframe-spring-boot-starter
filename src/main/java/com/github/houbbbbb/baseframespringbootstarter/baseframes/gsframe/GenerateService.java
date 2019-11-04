package com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe;

import com.github.houbbbbb.baseframespringbootstarter.properties.GenerateProperties;
import com.github.houbbbbb.baseframespringbootstarter.utils.FileUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.StrUtils;
import com.github.houbbbbb.baseframespringbootstarter.utils.TypeUtils;
import org.springframework.stereotype.Component;

/**
 * ClassName GenerateService
 * Description 生成service
 * Author hbw
 * Date 2019/6/1 10:43
 * Version 1.0
 **/
@Component
public class GenerateService {
    private GenerateProperties generateProperties;

    public GenerateService(GenerateProperties generateProperties) {
        this.generateProperties = generateProperties;
    }

    public void create(String table) {
        String className = TypeUtils.getClassName(table);
        String serviceName = className+"Service";
        StringBuilder sb = new StringBuilder();
        createHeader(className, sb);
        String dirPath = StrUtils.concat(generateProperties.getEnPath(), "/service/");
        FileUtils.mkDir(dirPath);
        String mapperPath = dirPath+serviceName+".java";
        FileUtils.writeOne(mapperPath, sb.toString());
    }

    public void createHeader(String className, StringBuilder sb) {
        String serviceName = className+"Service";
        String mapperName = className+"Mapper";
        String objName = TypeUtils.firstLower(className);
        String objMapper = TypeUtils.firstLower(mapperName);
        String pack = StrUtils.concat(generateProperties.getEnPack(), ".service");
        sb.append("package ").append(pack).append(";\n\n");
        sb.append("@Service\n");
        sb.append("public class ").append(serviceName)
          .append(" extends BService<").append(mapperName).append("> {\n");
        sb.append("\t@Autowired\n");
        sb.append("\tpublic "+serviceName+" (").append(mapperName).append(" dao) {\n")
                .append("\t\tthis.dao = dao;\n")
                .append("\t}");
        sb.append("\n}");
    }
}
