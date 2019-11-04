package com.github.houbbbbb.baseframespringbootstarter.baseframes.bsframe.dao;

import java.util.List;

/**
 * ClassName IMapper
 * Description dao层基本模板接口
 * Author hbw
 * Date 2019/5/9 13:22
 * Version 1.0
 **/
public interface IDao {

    <T> T selectOne(T t);

    <T> List<T> selectAll(T t);

    <T> Long selectCount(T t);

    <T> void insertOne(T t);

    <T> void insertAll(List<T> ts);

    <T> void delete(T t);

    <T> void update(T t);

}
