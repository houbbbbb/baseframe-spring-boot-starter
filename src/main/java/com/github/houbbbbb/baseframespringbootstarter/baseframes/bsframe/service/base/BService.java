package com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.service.base;

import com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.dao.IDao;
import com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.service.IService;

import java.util.List;

/**
 * ClassName BService
 * Description 基本Service模板
 * Author hbw
 * Date 2019/5/30 16:02
 * Version 1.0
 **/
public class BService<D extends IDao> implements IService {
    public D dao;

    @Override
    public <T> T queryOne(T t){ return dao.selectOne(t); }

    @Override
    public <T> List<T> queryAll(T t){ return dao.selectAll(t); }

    @Override
    public <T> Long queryCount(T t){ return dao.selectCount(t); }

    @Override
    public <T> void saveOne(T t){
        dao.insertOne(t);
    }

    @Override
    public <T> void saveAll(List<T> ts){ dao.insertAll(ts); }

    @Override
    public <T> void remove(T t){
        dao.delete(t);
    }

    @Override
    public <T> void modify(T t){
        dao.update(t);
    }

}
