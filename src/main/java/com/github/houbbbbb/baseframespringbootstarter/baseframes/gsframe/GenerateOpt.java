package com.github.houbbbbb.baseframespringbootstarter.baseframes.gsframe;

import com.github.houbbbbb.baseframespringbootstarter.utils.NullUtils;

import java.util.Map;

/**
 * ClassName GenerateOpt
 * Description 系统生成操作
 * Author hbw
 * Date 2019/6/1 11:37
 * Version 1.0
 **/
public class GenerateOpt {
    private GenerateRoot generateRoot;
    private GenerateEntity generateEntity;
    private GenerateMapper generateMapper;
    private GenerateMapperXml generateMapperXml;
    private GenerateService generateService;

    public GenerateOpt(GenerateRoot generateRoot, GenerateEntity generateEntity, GenerateMapper generateMapper, GenerateMapperXml generateMapperXml, GenerateService generateService) {
        this.generateRoot = generateRoot;
        this.generateEntity = generateEntity;
        this.generateMapper = generateMapper;
        this.generateMapperXml = generateMapperXml;
        this.generateService = generateService;
    }

    public void create(String... tables) {
        if(null!=tables && tables.length>0) {
            for (String table : tables) {
                if(NullUtils.isNotNull(table)) {
                    Map<String, String> map = generateRoot.getMap(table);
                    generateEntity.create(table, map);
                    generateMapper.create(table);
                    generateMapperXml.create(map, table);
                    generateService.create(table);
                }
            }
        }
    }
}
